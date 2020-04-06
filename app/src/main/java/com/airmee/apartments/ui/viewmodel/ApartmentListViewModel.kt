package com.airmee.apartments.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airmee.apartments.domain.entity.Apartment
import com.airmee.apartments.domain.entity.SearchParameters
import com.airmee.apartments.domain.usecase.GetApartmentListUseCase
import com.airmee.apartments.domain.usecase.PerformBookingUseCase
import com.airmee.apartments.ui.presenters.ApartmentListFragmentArgs
import kotlinx.coroutines.launch

internal class ApartmentListViewModel(
    private val apartmentListUseCase: GetApartmentListUseCase,
    private val performBookingUseCase: PerformBookingUseCase,
    private val args: ApartmentListFragmentArgs
) : ViewModel() {

    private val _uiState = MutableLiveData<UIStateModel>()
    val uiState: LiveData<UIStateModel>
        get() = _uiState

    fun fetchApartmentList() {
        _uiState.value = UIStateModel.Loading
        viewModelScope.launch {
            apartmentListUseCase.invoke().also { list ->
                if (list.isNotEmpty()) {
                    filterList().also {
                        val apartmentsExcludingBookings =
                            performBookingUseCase.getApartmentsExcludingBookings(it)
                        _uiState.value = UIStateModel.Content(apartmentsExcludingBookings)
                    }
                } else {
                    _uiState.value = UIStateModel.Empty("")
                }
            }
        }
    }

    private suspend fun filterList(): List<Apartment> {
        return apartmentListUseCase.filter(
            SearchParameters(
                args.latitude.toDouble(),
                args.longitude.toDouble(),
                args.bedroomCount
            )
        )
    }

    fun applyBooking(id: String) {
        viewModelScope.launch {
            performBookingUseCase.book(
                id,
                args.latitude.toDouble(),
                args.longitude.toDouble()
            )
        }
        fetchApartmentList()
    }

    sealed class UIStateModel {
        object Loading : UIStateModel()
        data class Content(val apartments: List<Apartment>) : UIStateModel()
        data class Error(val message: String) : UIStateModel()
        data class Empty(val message: String) : UIStateModel()
    }
}
package com.airmee.apartments.ui.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airmee.apartments.domain.usecase.GetUserLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApartmentBookingViewModel(private val userLocationUseCase: GetUserLocationUseCase) :
    ViewModel() {

    private val _userLocation = MutableLiveData<Location>()
    val userLocation: LiveData<Location>
        get() = _userLocation

    private val _userAddress = MutableLiveData<String>()
    val userAddress: LiveData<String>
        get() = _userAddress

    private val _date = MutableLiveData<String>()
        .apply { this.value = "Pick date" }
    val date: LiveData<String>
        get() = _date

    private val _bedroomsAmount = MutableLiveData<Int>()
        .apply { this.value = MIN_BED_VALUE }
    val bedroomsAmount: LiveData<Int>
        get() = _bedroomsAmount

    companion object {
        const val MAX_BED_VALUE = 5
        const val MIN_BED_VALUE = 0
    }

    var defaultValue = 0
    private val stepValue = 1

    fun displayUserLocation() {
        viewModelScope.launch(Dispatchers.Main) {
            runCatching {
                userLocationUseCase.getCurrentLocation()
            }.onFailure {
                _userAddress.value = "Error" // todo
            }.onSuccess {
                _userLocation.value = it
                _userAddress.value =
                    it?.let { it1 -> userLocationUseCase.getLocationTextAddress(it1) }
                        ?: run {
                            "Can't find your location"
                        }.also {
                            userLocationUseCase.getCurrentLocation()
                        }
            }
        }
    }

    fun setHardcodeLocation() {
        val defaultLocation = Location("")
        defaultLocation.latitude = 59.329440
        defaultLocation.longitude = 18.069124
        _userLocation.value = defaultLocation
        _userAddress.value = userLocationUseCase.getLocationTextAddress(defaultLocation)
    }

    fun saveDateValues(chosenDate: String) {
        _date.value = chosenDate // null checks todo
    }

    fun incrementValue() {
        _bedroomsAmount.value?.let {
            if (it < MAX_BED_VALUE) {
                updateCounter(it + stepValue);
            }
        }
    }

    fun decrementValue() {
        _bedroomsAmount.value?.let {
            if (it > MIN_BED_VALUE) {
                updateCounter(it - stepValue);
            }
        }
    }

    private fun updateCounter(newValue: Int) {
        var value = newValue
        when {
            newValue < MIN_BED_VALUE -> value = defaultValue
            newValue > MAX_BED_VALUE -> value = defaultValue
        }
        _bedroomsAmount.value = value
        defaultValue = value
    }
}
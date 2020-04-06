package com.airmee.apartments.data.repository

import android.location.Location
import com.airmee.apartments.data.model.ApartmentModel
import com.airmee.apartments.data.model.toDomain
import com.airmee.apartments.data.retrofit.ApartmentRetrofitService
import com.airmee.apartments.domain.entity.Apartment
import com.airmee.apartments.domain.entity.SearchParameters
import com.airmee.apartments.utils.LocationProvider
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class ApartmentRepositoryImpl(
    private val apartmentRetrofitService: ApartmentRetrofitService,
    private val locationProvider: LocationProvider
) : ApartmentRepository {

    private val apartments = mutableListOf<ApartmentModel>()

    override suspend fun getList(): List<ApartmentModel> = execute {
        apartmentRetrofitService.retrieveApartments()
            .also {
                if (apartments.isEmpty()) {
                    apartments.addAll(it)
                }
            }
    }

    override suspend fun filterList(searchParameters: SearchParameters): List<Apartment> {
        val userLocation = Location("").apply {
            latitude = searchParameters.lat
            longitude = searchParameters.long
        }
        return apartments
            .filterNot { it.bedrooms < searchParameters.filterBedroomsNumber }
            .map {
                val apartmentLocation = Location("").apply {
                    latitude = it.latitude
                    longitude = it.longitude
                }
                val distanceTo =
                    locationProvider.distanceBetweenLocations(userLocation, apartmentLocation)
                val address = locationProvider.fetchAddressFromLocation(it.latitude, it.longitude)
                it.toDomain(distanceTo, address)
            }
            .sortedBy { it.distanceTo }
    }

    private suspend fun <T> execute(function: suspend () -> List<T>): List<T> {
        return try {
            function()
        } catch (e: UnknownHostException) {
            emptyList()
        } catch (e: SocketTimeoutException) {
            emptyList()
        }
    }
}
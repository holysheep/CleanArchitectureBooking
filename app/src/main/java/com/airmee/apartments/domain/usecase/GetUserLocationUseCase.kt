package com.airmee.apartments.domain.usecase

import android.location.Location
import com.airmee.apartments.utils.LocationProvider

class GetUserLocationUseCase(private val locationProvider: LocationProvider) {

    suspend fun getCurrentLocation(): Location? {
        return locationProvider.getCurrentLocationAsync().await()
    }

    fun getLocationTextAddress(currentLocation: Location): String {
        return currentLocation.let {
            locationProvider.fetchAddressFromLocation(
                currentLocation.latitude,
                currentLocation.longitude
            )
        }
    }
}
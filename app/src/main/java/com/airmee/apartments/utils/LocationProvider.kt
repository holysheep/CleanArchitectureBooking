package com.airmee.apartments.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import java.text.NumberFormat

class LocationProvider(
    private val fusedLocationClient: FusedLocationProviderClient,
    private val geocoder: Geocoder,
    private val context: Context
) {

    companion object {
        const val PERMISSION_ACCESS_LOCATION = 1
    }

    private val appContext = context.applicationContext


    fun getCurrentLocationAsync(): Deferred<Location?> {
        return run {
            if (isLocationPermissionGranted())
                fusedLocationClient.lastLocation.asDeferred()
            else throw PermissionLocationNotGranted()
        }
    }

    // todo
    class PermissionLocationNotGranted : Exception()

    fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermissions(fragment: Fragment) {
        fragment.requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ACCESS_LOCATION
        )
    }

    fun fetchAddressFromLocation(
        lat: Double,
        long: Double
    ): String {
        return geocoder.getFromLocation(lat, long, 1)
            .firstOrNull()
            ?.let {
                return listOfNotNull(
                    it.getAddressLine(0)
                ).joinToString()
            } ?: run { "" }
    }

    fun distanceBetweenLocations(a: Location, b: Location) = a.distanceTo(b)
    fun distanceBetweenLocationsText(a: Location, b: Location) = a.distanceTo(b).format()

    private fun <T> Task<T>.asDeferred(): Deferred<T?> =
        CompletableDeferred<T>().apply {
            addOnSuccessListener { result -> complete(result) }
            addOnFailureListener { exception -> completeExceptionally(exception) }
        }
}
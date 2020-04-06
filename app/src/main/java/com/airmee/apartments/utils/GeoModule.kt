package com.airmee.apartments.utils

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.location.LocationServices
import org.kodein.di.Kodein
import org.kodein.di.generic.*

internal val geoModule = Kodein.Module("GeoModule") {

    bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
    bind() from provider { LocationProvider(instance(), instance(), instance()) }
    bind<Geocoder>() with singleton { Geocoder(instance()) }
}

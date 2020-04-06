package com.airmee.apartments.data.retrofit

import com.airmee.apartments.data.model.ApartmentModel
import retrofit2.http.GET

internal interface ApartmentRetrofitService {
    @GET("apartments.json")
    suspend fun retrieveApartments(): List<ApartmentModel>
}
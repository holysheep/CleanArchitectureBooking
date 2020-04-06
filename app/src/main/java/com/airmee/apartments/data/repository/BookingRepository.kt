package com.airmee.apartments.data.repository

import com.airmee.apartments.domain.entity.Apartment


internal interface BookingRepository {
    suspend fun saveBooking(
        id: String,
        startDate: Double,
        endDate: Double
    )

    fun isReplicatedApartment(apartment: Apartment): Boolean
}
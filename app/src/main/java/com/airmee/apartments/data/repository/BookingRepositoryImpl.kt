package com.airmee.apartments.data.repository

import com.airmee.apartments.domain.entity.Apartment
import com.airmee.apartments.domain.entity.Booking

class BookingRepositoryImpl : BookingRepository {

    private val savedBookings = mutableListOf<Booking>()

    override suspend fun saveBooking(id: String, startDate: Double, endDate: Double) {
        savedBookings.add(Booking(id, startDate, endDate))
    }

    override fun isReplicatedApartment(apartment: Apartment): Boolean {
        return savedBookings
            .map { it.id }
            .contains(apartment.id)
    }

}
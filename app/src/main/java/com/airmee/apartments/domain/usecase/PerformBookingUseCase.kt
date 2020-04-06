package com.airmee.apartments.domain.usecase

import com.airmee.apartments.data.repository.BookingRepository
import com.airmee.apartments.domain.entity.Apartment

internal class PerformBookingUseCase(private val bookingRepository: BookingRepository) {

    suspend fun book(id: String, startDate: Double, endDate: Double) =
        bookingRepository.saveBooking(id, startDate, endDate)

    fun getApartmentsExcludingBookings(apartmentList: List<Apartment>): List<Apartment> {
        return apartmentList.filterNot {
            bookingRepository.isReplicatedApartment(it)
        }.map {
            Apartment(it.id, it.bedrooms, it.name, it.distanceTo, it.address)
        }
    }
}
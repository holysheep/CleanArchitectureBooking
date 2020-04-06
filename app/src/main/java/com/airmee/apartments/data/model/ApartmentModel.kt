package com.airmee.apartments.data.model

import com.airmee.apartments.domain.entity.Apartment

data class ApartmentModel(val id: String,
                          val bedrooms: Int,
                          val name: String,
                          val latitude: Double,
                          val longitude: Double)

internal fun ApartmentModel.toDomain(distance: Float, address: String) : Apartment =
    Apartment(id, bedrooms, name, distance, address)


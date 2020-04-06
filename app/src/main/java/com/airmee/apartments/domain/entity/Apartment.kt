package com.airmee.apartments.domain.entity

data class Apartment(
    val id: String,
    val bedrooms: Int,
    val name: String,
    val distanceTo: Float,
    val address: String
)

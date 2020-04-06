package com.airmee.apartments.domain.entity

// Search criteria class for filtering apartment list
data class SearchParameters(
    val lat: Double,
    val long: Double,
    val filterBedroomsNumber: Int
)

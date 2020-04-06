package com.airmee.apartments.data.repository

import com.airmee.apartments.data.model.ApartmentModel
import com.airmee.apartments.domain.entity.Apartment
import com.airmee.apartments.domain.entity.SearchParameters

internal interface ApartmentRepository {
    suspend fun getList(): List<ApartmentModel>
    suspend fun filterList(searchParameters: SearchParameters): List<Apartment>
}
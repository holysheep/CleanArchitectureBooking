package com.airmee.apartments.domain.usecase

import com.airmee.apartments.data.model.ApartmentModel
import com.airmee.apartments.data.repository.ApartmentRepository
import com.airmee.apartments.domain.entity.SearchParameters

internal class GetApartmentListUseCase(private val apartmentRepository: ApartmentRepository) {

    suspend fun invoke(): List<ApartmentModel> =
        apartmentRepository.getList()

    suspend fun filter(searchParameters: SearchParameters) =
        apartmentRepository.filterList(searchParameters)
}
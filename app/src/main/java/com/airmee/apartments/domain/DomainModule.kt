package com.airmee.apartments.domain

import com.airmee.apartments.domain.usecase.GetApartmentListUseCase
import com.airmee.apartments.domain.usecase.GetUserLocationUseCase
import com.airmee.apartments.domain.usecase.PerformBookingUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("DomainModule") {

    bind() from singleton { GetApartmentListUseCase(instance()) }
    bind() from singleton { GetUserLocationUseCase(instance()) }
    bind() from singleton { PerformBookingUseCase(instance()) }
}
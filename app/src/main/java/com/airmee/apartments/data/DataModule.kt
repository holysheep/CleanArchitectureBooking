package com.airmee.apartments.data

import android.content.Context
import com.airmee.apartments.data.repository.ApartmentRepository
import com.airmee.apartments.data.repository.ApartmentRepositoryImpl
import com.airmee.apartments.data.repository.BookingRepositoryImpl
import com.airmee.apartments.data.retrofit.ApartmentRetrofitService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("DataModule") {

    bind<ApartmentRepository>() with singleton { ApartmentRepositoryImpl(instance(), instance()) }
    bind<BookingRepositoryImpl>() with singleton { BookingRepositoryImpl() }
    bind() from singleton { instance<Retrofit>().create(ApartmentRetrofitService::class.java) }
}
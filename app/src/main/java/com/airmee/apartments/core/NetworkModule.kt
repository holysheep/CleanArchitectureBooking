package com.airmee.apartments.core

import com.airmee.apartments.BuildConfig
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = Kodein.Module("networkModule") {

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }
    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>().build()
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }
    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BuildConfig.AMAZON_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }
}
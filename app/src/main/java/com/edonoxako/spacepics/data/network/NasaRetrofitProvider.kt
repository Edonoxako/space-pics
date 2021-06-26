package com.edonoxako.spacepics.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NasaRetrofitProvider(
    private val moshiConverterProvider: MoshiConverterProvider
) {

    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_NASA_URL)
            .addConverterFactory(moshiConverterProvider.getMoshiConverterFactory())
            .build()
    }

    companion object {
        private const val BASE_NASA_URL = "https://api.nasa.gov"
    }
}
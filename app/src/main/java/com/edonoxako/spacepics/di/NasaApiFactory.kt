package com.edonoxako.spacepics.di

import com.edonoxako.spacepics.data.network.MoshiConverterProvider
import com.edonoxako.spacepics.data.network.NasaApi
import com.edonoxako.spacepics.data.network.NasaRetrofitProvider

class NasaApiFactory {

    fun getNasaApi(): NasaApi {
        val moshiConverterProvider = MoshiConverterProvider()
        val nasaRetrofitProvider = NasaRetrofitProvider(moshiConverterProvider)
        return nasaRetrofitProvider.retrofit.create(NasaApi::class.java)
    }
}
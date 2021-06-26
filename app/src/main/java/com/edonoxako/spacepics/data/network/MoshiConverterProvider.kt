package com.edonoxako.spacepics.data.network

import retrofit2.converter.moshi.MoshiConverterFactory

class MoshiConverterProvider {

    fun getMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }
}
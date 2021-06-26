package com.edonoxako.spacepics.data.network

import retrofit2.http.GET

interface NasaApi {

    @GET("/planetary/apod?api_key=DEMO_KEY")
    suspend fun getApod(): ApodDto
}
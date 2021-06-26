package com.edonoxako.spacepics.data.network

import com.squareup.moshi.Json

data class ApodDto(
    @Json(name = "copyright")
    val copyright: String?,

    @Json(name = "date")
    val date: String?,

    @Json(name = "explanation")
    val explanation: String?,

    @Json(name = "hdurl")
    val hdurl: String?,

    @Json(name = "media_type")
    val mediaType: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "url")
    val url: String?
)

package com.edonoxako.spacepics.data.storage

import com.edonoxako.spacepics.picturedetails.PictureDetails

sealed class CacheResult {

    data class Hit(
        val pictureDetails: PictureDetails
    ) : CacheResult()

    object Miss : CacheResult()
}
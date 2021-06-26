package com.edonoxako.spacepics.picturedetails

interface RemotePictureDetailsRepository {
    suspend fun getPictureDetails(): PictureDetails
}
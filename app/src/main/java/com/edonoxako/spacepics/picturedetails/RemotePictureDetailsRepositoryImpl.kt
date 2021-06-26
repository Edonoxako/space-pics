package com.edonoxako.spacepics.picturedetails

import com.edonoxako.spacepics.data.network.NasaApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemotePictureDetailsRepositoryImpl(
    private val nasaApi: NasaApi,
    private val pictureDetailsMapper: PictureDetailsMapper
) : RemotePictureDetailsRepository {

    override suspend fun getPictureDetails(): PictureDetails {
        return withContext(Dispatchers.IO) {
            nasaApi.getApod().let(pictureDetailsMapper::map)
        }
    }
}
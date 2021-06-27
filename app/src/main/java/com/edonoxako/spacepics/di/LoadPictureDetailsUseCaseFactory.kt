package com.edonoxako.spacepics.di

import android.content.Context
import com.edonoxako.spacepics.data.storage.PictureDetailsCache
import com.edonoxako.spacepics.picturedetails.*

//todo replace this with normal DI
class LoadPictureDetailsUseCaseFactory(
    context: Context
) {

    private val pictureDetailsCache by lazy {
        val pictureDetailsMapper = PictureDetailsMapper()
        PictureDetailsCache(
            context = context,
            pictureDetailsMapper = pictureDetailsMapper
        )
    }

    private val productionRepository by lazy {
        val nasaApi = NasaApiFactory().getNasaApi()
        val pictureDetailsMapper = PictureDetailsMapper()

        RemotePictureDetailsRepositoryImpl(
            nasaApi = nasaApi,
            pictureDetailsMapper = pictureDetailsMapper
        )
    }

    private val mockRepository by lazy {
        MockPictureDetailsRepository()
    }

    fun getLoadPictureDetailsUseCase(): LoadPictureDetailsUseCase {
        return LoadPictureDetailsUseCase(
            remotePictureDetailsRepository = productionRepository,
            pictureDetailsCache = pictureDetailsCache,
            dateProvider = DateProvider()
        )
    }
}
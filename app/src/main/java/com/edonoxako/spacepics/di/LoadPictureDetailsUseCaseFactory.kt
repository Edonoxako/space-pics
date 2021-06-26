package com.edonoxako.spacepics.di

import com.edonoxako.spacepics.data.storage.PictureDetailsCache
import com.edonoxako.spacepics.picturedetails.*

class LoadPictureDetailsUseCaseFactory {

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
            pictureDetailsCache = PictureDetailsCache(),
            dateProvider = DateProvider()
        )
    }
}
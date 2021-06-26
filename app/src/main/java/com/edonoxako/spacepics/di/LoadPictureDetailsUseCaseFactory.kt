package com.edonoxako.spacepics.di

import com.edonoxako.spacepics.picturedetails.LoadPictureDetailsUseCase
import com.edonoxako.spacepics.picturedetails.MockPictureDetailsRepository
import com.edonoxako.spacepics.picturedetails.PictureDetailsMapper
import com.edonoxako.spacepics.picturedetails.RemotePictureDetailsRepositoryImpl

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
        return LoadPictureDetailsUseCase(productionRepository)
    }
}
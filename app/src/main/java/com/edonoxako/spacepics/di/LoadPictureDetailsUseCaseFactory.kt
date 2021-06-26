package com.edonoxako.spacepics.di

import com.edonoxako.spacepics.picturedetails.LoadPictureDetailsUseCase
import com.edonoxako.spacepics.picturedetails.MockPictureDetailsRepository
import com.edonoxako.spacepics.picturedetails.PictureDetailsMapper
import com.edonoxako.spacepics.picturedetails.RemotePictureDetailsRepositoryImpl

class LoadPictureDetailsUseCaseFactory {

    fun getLoadPictureDetailsUseCase(): LoadPictureDetailsUseCase {
        val nasaApi = NasaApiFactory().getNasaApi()
        val pictureDetailsMapper = PictureDetailsMapper()

        val pictureDetailsRepository = RemotePictureDetailsRepositoryImpl(
            nasaApi = nasaApi,
            pictureDetailsMapper = pictureDetailsMapper
        )

        return LoadPictureDetailsUseCase(pictureDetailsRepository)
    }
}
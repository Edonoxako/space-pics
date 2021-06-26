package com.edonoxako.spacepics.picturedetails

class LoadPictureDetailsUseCase(
    private val remotePictureDetailsRepository: RemotePictureDetailsRepository
) {

    suspend fun execute(): PictureDetails {
        return remotePictureDetailsRepository.getPictureDetails()
    }
}
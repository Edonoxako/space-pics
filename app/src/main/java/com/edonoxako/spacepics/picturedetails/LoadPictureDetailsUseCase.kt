package com.edonoxako.spacepics.picturedetails

class LoadPictureDetailsUseCase(
    private val pictureDetailsRepository: PictureDetailsRepository
) {

    suspend fun execute(): PictureDetails {
        return pictureDetailsRepository.getPictureDetails()
    }
}
package com.edonoxako.spacepics.picturedetails

import com.edonoxako.spacepics.data.storage.CacheResult
import com.edonoxako.spacepics.data.storage.PictureDetailsCache

class LoadPictureDetailsUseCase(
    private val remotePictureDetailsRepository: RemotePictureDetailsRepository,
    private val pictureDetailsCache: PictureDetailsCache,
    private val dateProvider: DateProvider
) {

    suspend fun execute(): PictureDetails {
        val currentDate = dateProvider.getCurrentDateFormatted()
        val cacheResult = pictureDetailsCache.getByDate(currentDate)
        return if (cacheResult is CacheResult.Hit) {
            cacheResult.pictureDetails
        } else {
            remotePictureDetailsRepository.getPictureDetails().apply {
                pictureDetailsCache.putByDate(currentDate, this)
            }
        }
    }
}
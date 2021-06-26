package com.edonoxako.spacepics.data.storage

import com.edonoxako.spacepics.picturedetails.PictureDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

class PictureDetailsCache {

    private val pictureDetailsMap = ConcurrentHashMap<String, PictureDetails>()

    suspend fun getByDate(date: String): CacheResult {
        return withContext(Dispatchers.IO) {
            pictureDetailsMap[date]?.let(CacheResult::Hit) ?: CacheResult.Miss
        }
    }

    suspend fun putByDate(date: String, pictureDetails: PictureDetails) {
        withContext(Dispatchers.IO) {
            pictureDetailsMap[date] = pictureDetails
        }
    }
}
package com.edonoxako.spacepics.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.edonoxako.spacepics.PictureDetailsCacheProto
import com.edonoxako.spacepics.picturedetails.PictureDetails
import com.edonoxako.spacepics.picturedetails.PictureDetailsMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

class PictureDetailsCache(
    private val context: Context,
    private val pictureDetailsMapper: PictureDetailsMapper
) {

    private val Context.cacheDateStore: DataStore<PictureDetailsCacheProto> by dataStore(
        fileName = CACHE_FILE,
        serializer = PictureDetailsCacheSerializer
    )

    suspend fun getByDate(date: String): CacheResult {
        return withContext(Dispatchers.IO) {
            //todo this code may produce bugs and should be cleaned up
            context.cacheDateStore
                .data
                .map { cacheHolder -> cacheHolder.cacheMap[date] }
                .map { it?.let(pictureDetailsMapper::map) }
                .firstOrNull()
                ?.let(CacheResult::Hit)
                ?: CacheResult.Miss
        }
    }

    suspend fun putByDate(date: String, pictureDetails: PictureDetails) {
        withContext(Dispatchers.IO) {
            context.cacheDateStore.updateData { cache ->
                cache.toBuilder()
                    .putCache(date, pictureDetailsMapper.map(pictureDetails))
                    .build()
            }
        }
    }

    companion object {
        private const val CACHE_FILE = "picture_cache.pb"
    }
}
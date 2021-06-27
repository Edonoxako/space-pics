package com.edonoxako.spacepics.data.storage

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.edonoxako.spacepics.PictureDetailsCacheProto
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object PictureDetailsCacheSerializer : Serializer<PictureDetailsCacheProto> {

    override val defaultValue: PictureDetailsCacheProto
        get() = PictureDetailsCacheProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): PictureDetailsCacheProto {
        return try {
            PictureDetailsCacheProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: PictureDetailsCacheProto, output: OutputStream) {
        t.writeTo(output)
    }
}
package com.pt.chat.data.local.converters

import androidx.room.TypeConverter
import com.pt.chat.data.local.entities.AttachmentEntity
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    @TypeConverter
    fun fromAttachmentList(value: List<AttachmentEntity>?): String {
        return try {
            json.encodeToString(value ?: emptyList())
        } catch (e: Exception) {
            ""
        }
    }

    @TypeConverter
    fun toAttachmentList(value: String): List<AttachmentEntity>? {
        return try {
            json.decodeFromString(value)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
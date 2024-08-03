package com.pt.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pt.database.entities.AttachmentEntity

class Converters {
    @TypeConverter
    fun fromAttachmentList(value: List<AttachmentEntity>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<AttachmentEntity>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toAttachmentList(value: String): List<AttachmentEntity>? {
        val gson = Gson()
        val type = object : TypeToken<List<AttachmentEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
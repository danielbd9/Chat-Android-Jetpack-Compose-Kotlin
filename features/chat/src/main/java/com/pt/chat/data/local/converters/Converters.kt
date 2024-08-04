package com.pt.chat.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pt.chat.data.local.entities.AttachmentEntity

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

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

package com.pt.chat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pt.chat.data.local.converters.Converters

@Entity(tableName = "messages")
@TypeConverters(Converters::class)
data class MessageEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val content: String?,
    val timestamp: Long,
    val attachments: List<AttachmentEntity>?
)
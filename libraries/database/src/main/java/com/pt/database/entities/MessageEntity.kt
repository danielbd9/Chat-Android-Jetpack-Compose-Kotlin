package com.pt.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pt.database.Converters

@Entity(tableName = "messages")
@TypeConverters(Converters::class)
data class MessageEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val content: String?,
    val timestamp: Long,
    val attachments: List<AttachmentEntity>?
)
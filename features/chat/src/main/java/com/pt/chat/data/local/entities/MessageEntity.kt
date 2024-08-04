package com.pt.chat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pt.chat.data.local.converters.Converters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "messages")
@TypeConverters(Converters::class)
@Serializable
data class MessageEntity(
    @PrimaryKey
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("content")
    val content: String?,
    @SerialName("timestamp")
    val timestamp: Long = 0L,
    @SerialName("attachments")
    val attachments: List<AttachmentEntity>?
)

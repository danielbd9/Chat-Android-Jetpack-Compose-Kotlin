package com.pt.chat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "attachments")
@Serializable
data class AttachmentEntity(
    @PrimaryKey @SerialName("id") val id: String,
    @SerialName("messageId") val messageId: Int,
    @SerialName("title") val title: String?,
    @SerialName("url") val url: String?,
    @SerialName("thumbnailUrl") val thumbnailUrl: String?
)
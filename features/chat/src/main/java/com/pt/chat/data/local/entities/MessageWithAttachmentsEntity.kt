package com.pt.chat.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageWithAttachmentsEntity(
    @Embedded @SerialName("message") val message: MessageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "messageId"
    )
    @SerialName("attachments") val attachments: List<AttachmentEntity>?
)
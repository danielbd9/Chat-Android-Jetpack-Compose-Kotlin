package com.pt.chat.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MessageWithAttachmentsEntity(
    @Embedded val message: MessageEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "messageId"
    )
    val attachments: List<AttachmentEntity>?
)
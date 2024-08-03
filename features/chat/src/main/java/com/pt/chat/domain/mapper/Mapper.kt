package com.pt.chat.domain.mapper

import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = this.id,
        userId = this.userId,
        content = this.content,
        timestamp = this.timestamp,
        attachments = this.attachments?.map { it.toEntity(this.id) }
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        avatarId = this.avatarId
    )
}

fun Attachment.toEntity(messageId: Int): AttachmentEntity {
    return AttachmentEntity(
        id = this.id,
        messageId = messageId,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}

fun MessageEntity.toDomain(attachments: List<Attachment>): Message {
    return Message(
        id = this.id,
        userId = this.userId,
        content = this.content,
        timestamp = this.timestamp,
        attachments = attachments
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        name = this.name,
        avatarId = this.avatarId
    )
}

fun AttachmentEntity.toDomain(): Attachment {
    return Attachment(
        id = this.id,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )
}
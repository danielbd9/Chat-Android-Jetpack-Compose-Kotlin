package com.pt.chat.domain.mapper

import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity

fun Message.toEntity(): MessageEntity {
    return MessageEntity(id, userId, content, timestamp)
}

fun User.toEntity(): UserEntity {
    return UserEntity(id, name, avatarId)
}

fun Attachment.toEntity(): AttachmentEntity {
    return AttachmentEntity(id, id, title, url, thumbnailUrl)
}

fun MessageEntity.toDomain(attachments: List<Attachment> = emptyList()): Message {
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
        avatarId = this.avatarId,
        name = this.name
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
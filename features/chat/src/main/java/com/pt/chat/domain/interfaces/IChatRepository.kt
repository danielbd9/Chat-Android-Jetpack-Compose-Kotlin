package com.pt.chat.domain.interfaces

import com.pt.chat.domain.model.MessageList
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity


interface IChatRepository {
    suspend fun saveMessages(messages: List<MessageEntity>)
    suspend fun saveUsers(users: List<UserEntity>)
    suspend fun saveAttachments(attachments: List<AttachmentEntity>)
    suspend fun getMessages(): MessageList
    suspend fun fetchMessagesFromApi(): MessageList
}
package com.pt.chat.domain.interfaces

import com.pt.chat.data.local.entities.AttachmentEntity
import com.pt.chat.data.local.entities.MessageEntity
import com.pt.chat.data.local.entities.UserEntity
import com.pt.chat.domain.model.MessageList


interface IChatRepository {
    suspend fun saveMessages(messages: List<MessageEntity>)
    suspend fun saveUsers(users: List<UserEntity>)
    suspend fun saveAttachments(attachments: List<AttachmentEntity>)
    suspend fun getMessages(): MessageList
    suspend fun fetchMessagesFromApi(): MessageList
}
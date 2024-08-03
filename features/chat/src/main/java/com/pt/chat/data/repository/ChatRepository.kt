package com.pt.chat.data.repository

import com.pt.chat.data.service.ApiService
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.mapper.toDomain
import com.pt.chat.domain.model.MessageList
import com.pt.database.dao.AttachmentDao
import com.pt.database.dao.MessageDao
import com.pt.database.dao.UserDao
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity

class ChatRepositoryImpl(
    private val apiService: ApiService,
    private val messageDao: MessageDao,
    private val userDao: UserDao,
    private val attachmentDao: AttachmentDao
) : IChatRepository {

    override suspend fun saveMessages(messages: List<MessageEntity>) {
        messageDao.insertAll(messages)
    }

    override suspend fun saveUsers(users: List<UserEntity>) {
        userDao.insertAll(users)
    }

    override suspend fun saveAttachments(attachments: List<AttachmentEntity>) {
        attachmentDao.insertAll(attachments)
    }

    override suspend fun getMessages(): MessageList {
        val messagesWithAttachments = messageDao.getMessagesWithAttachments()
        val messages = messagesWithAttachments.map { mwa ->
            val attachments = mwa.attachments?.map { it.toDomain() }
            mwa.message.toDomain(attachments.orEmpty())
        }
        val users = userDao.getAll().map { it.toDomain() }

        if (messages.isEmpty() || users.isEmpty()) {
            val messageList = apiService.fetchMessagesFromApi()
            return MessageList(messageList.messages, messageList.users, messageList.attachments)
        }

        val attachments = messages.flatMap { it.attachments.orEmpty() }

        return MessageList(messages, users, attachments)
    }

    override suspend fun fetchMessagesFromApi(): MessageList {
        val messageList = apiService.fetchMessagesFromApi()
        return MessageList(
            messages = messageList.messages,
            users = messageList.users,
            attachments = messageList.attachments ?: emptyList()
        )
    }
}
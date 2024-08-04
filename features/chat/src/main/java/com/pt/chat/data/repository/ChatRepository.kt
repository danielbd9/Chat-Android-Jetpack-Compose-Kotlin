package com.pt.chat.data.repository

import com.pt.chat.data.local.dao.AttachmentDao
import com.pt.chat.data.local.dao.MessageDao
import com.pt.chat.data.local.dao.UserDao
import com.pt.chat.data.local.entities.AttachmentEntity
import com.pt.chat.data.local.entities.MessageEntity
import com.pt.chat.data.local.entities.UserEntity
import com.pt.chat.domain.interfaces.IChatApiService
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.data.mapper.toDomain
import com.pt.chat.domain.model.MessageList

class ChatRepositoryImpl(
    private val apiService: IChatApiService,
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
            return MessageList(messageList.messages, messageList.users)
        }

        return MessageList(messages, users)
    }

    override suspend fun fetchMessagesFromApi(): MessageList {
        val messageList = apiService.fetchMessagesFromApi()
        return MessageList(
            messages = messageList.messages,
            users = messageList.users,
        )
    }
}
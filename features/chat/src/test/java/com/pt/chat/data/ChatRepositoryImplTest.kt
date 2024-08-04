package com.pt.chat.data

import com.pt.chat.data.local.dao.AttachmentDao
import com.pt.chat.data.local.dao.MessageDao
import com.pt.chat.data.local.dao.UserDao
import com.pt.chat.data.local.entities.AttachmentEntity
import com.pt.chat.data.local.entities.MessageEntity
import com.pt.chat.data.local.entities.MessageWithAttachmentsEntity
import com.pt.chat.data.local.entities.UserEntity
import com.pt.chat.data.repository.ChatRepositoryImpl
import com.pt.chat.domain.interfaces.IChatApiService
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.MessageList
import com.pt.chat.domain.model.User
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ChatRepositoryImplTest {

    private lateinit var apiService: IChatApiService
    private lateinit var messageDao: MessageDao
    private lateinit var userDao: UserDao
    private lateinit var attachmentDao: AttachmentDao
    private lateinit var chatRepository: ChatRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        messageDao = mockk()
        userDao = mockk()
        attachmentDao = mockk()
        chatRepository = ChatRepositoryImpl(apiService, messageDao, userDao, attachmentDao)
    }

    @Test
    fun `Given messages When saveMessages is called Then messageDao insertAll should be called`() = runTest {
        val messages = listOf(
            MessageEntity(1, 2, "Test message", 12345, emptyList())
        )

        coEvery { messageDao.insertAll(messages) } just Runs

        chatRepository.saveMessages(messages)

        coVerify { messageDao.insertAll(messages) }
    }

    @Test
    fun `Given users When saveUsers is called Then userDao insertAll should be called`() = runTest {
        val users = listOf(
            UserEntity(1, "Test User", "avatar")
        )

        coEvery { userDao.insertAll(users) } just Runs

        chatRepository.saveUsers(users)

        coVerify { userDao.insertAll(users) }
    }

    @Test
    fun `Given attachments When saveAttachments is called Then attachmentDao insertAll should be called`() = runTest {
        val attachments = listOf(
            AttachmentEntity(1.toString(), 1, "Test attachment", "", "")
        )

        coEvery { attachmentDao.insertAll(attachments) } just Runs

        chatRepository.saveAttachments(attachments)

        coVerify { attachmentDao.insertAll(attachments) }
    }

    @Test
    fun `Given messages and users available in dao When getMessages is called Then return messages and users from dao`() = runTest {
        val messagesWithAttachments = listOf(
            MessageWithAttachmentsEntity(
                message = MessageEntity(1, 2, "Test message", 12345, emptyList()),
                attachments = listOf(AttachmentEntity(1.toString(), 1, "Test attachment", "", ""))
            )
        )
        val users = listOf(
            UserEntity(2, "Test User", "avatar")
        )

        coEvery { messageDao.getMessagesWithAttachments() } returns messagesWithAttachments
        coEvery { userDao.getAll() } returns users

        val result = chatRepository.getMessages()

        val expectedMessages = listOf(
            Message(
                id = 1,
                userId = 2,
                content = "Test message",
                timestamp = 12345,
                attachments = listOf(com.pt.chat.domain.model.Attachment(1.toString(), "Test attachment", "", ""))
            )
        )
        val expectedUsers = listOf(
            User(id = 2, name = "Test User", avatarId = "avatar")
        )
        val expectedMessageList = MessageList(expectedMessages, expectedUsers)

        assertEquals(expectedMessageList, result)
    }

    @Test
    fun `Given empty dao When getMessages is called Then fetch messages from api`() = runTest {
        coEvery { messageDao.getMessagesWithAttachments() } returns emptyList()
        coEvery { userDao.getAll() } returns emptyList()

        val apiMessageList = MessageList(
            messages = listOf(
                Message(
                    id = 1,
                    userId = 2,
                    content = "Test message",
                    timestamp = 12345,
                    attachments = emptyList()
                )
            ),
            users = listOf(
                User(id = 2, name = "Test User", avatarId = "avatar")
            )
        )

        coEvery { apiService.fetchMessagesFromApi() } returns apiMessageList

        val result = chatRepository.getMessages()

        assertEquals(apiMessageList, result)
        coVerify { apiService.fetchMessagesFromApi() }
    }

    @Test
    fun `Given api service When fetchMessagesFromApi is called Then return messages from api`() = runTest {
        val apiMessageList = MessageList(
            messages = listOf(
                Message(
                    id = 1,
                    userId = 2,
                    content = "Test message",
                    timestamp = 12345,
                    attachments = emptyList()
                )
            ),
            users = listOf(
                User(id = 2, name = "Test User", avatarId = "avatar")
            )
        )

        coEvery { apiService.fetchMessagesFromApi() } returns apiMessageList

        val result = chatRepository.fetchMessagesFromApi()

        assertEquals(apiMessageList, result)
        coVerify { apiService.fetchMessagesFromApi() }
    }
}

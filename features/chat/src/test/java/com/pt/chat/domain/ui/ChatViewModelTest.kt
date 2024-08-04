package com.pt.chat.domain.ui

import com.pt.chat.domain.interactor.InitializeDataInteractor
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.MessageList
import com.pt.chat.domain.model.User
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.chat.presentation.ChatViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ChatViewModelTest {

    private lateinit var getMessagesUseCase: GetMessagesUseCase
    private lateinit var saveMessagesUseCase: SaveMessagesUseCase
    private lateinit var initializeDataInteractor: InitializeDataInteractor
    private lateinit var chatViewModel: ChatViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        getMessagesUseCase = mockk()
        saveMessagesUseCase = mockk()
        initializeDataInteractor = mockk()

        chatViewModel = spyk(
            ChatViewModel(
                getMessagesUseCase = getMessagesUseCase,
                saveMessagesUseCase = saveMessagesUseCase,
                initializeDataInteractor = initializeDataInteractor
            )
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given messages are loaded When initializeData is called Then messagesWithUsers should be updated`() = runTest {
        val mockMessages = listOf(
            Message(id = 1, userId = 2, content = "Test message", timestamp = 12345, attachments = emptyList())
        )
        val mockUsers = listOf(User(id = 2, name = "Test User", avatarId = "avatar"))
        val mockMessageList = MessageList(messages = mockMessages, users = mockUsers)

        coEvery { initializeDataInteractor.execute() } just Runs
        coEvery { getMessagesUseCase.execute(Unit) } returns mockMessageList

        chatViewModel.initializeData()

        val result = chatViewModel.messagesWithUsers.first()
        assertEquals(1, result.size)
        assertEquals(mockMessages[0], result[0].messages)
        assertEquals(mockUsers[0], result[0].users)

        coVerify { initializeDataInteractor.execute() }
        coVerify { getMessagesUseCase.execute(Unit) }
    }

    @Test
    fun `Given more messages are loaded When loadMoreMessages is called Then messagesWithUsers should be updated`() = runTest {
        val mockMessages = listOf(
            Message(id = 1, userId = 2, content = "Test message", timestamp = 12345, attachments = emptyList())
        )
        val mockUsers = listOf(User(id = 2, name = "Test User", avatarId = "avatar"))
        val mockMessageList = MessageList(messages = mockMessages, users = mockUsers)

        coEvery { getMessagesUseCase.execute(Unit) } returns mockMessageList

        chatViewModel.loadMoreMessages()

        val result = chatViewModel.messagesWithUsers.first()
        assertEquals(1, result.size)
        assertEquals(mockMessages[0], result[0].messages)
        assertEquals(mockUsers[0], result[0].users)

        chatViewModel.loadMoreMessages()
        val moreMessages = chatViewModel.messagesWithUsers.first()
        assertEquals(1, moreMessages.size)

        coVerify(exactly = 1) { getMessagesUseCase.execute(Unit) }
    }

    @Test
    fun `Given new message is sent When sendMessage is called Then messagesWithUsers should be updated`() = runTest {
        val mockMessages = listOf(
            Message(id = 1, userId = 2, content = "Test message", timestamp = 12345, attachments = emptyList())
        )
        val mockUsers = listOf(User(id = 2, name = "Test User", avatarId = "avatar"))
        val mockMessageList = MessageList(messages = mockMessages, users = mockUsers)

        coEvery { getMessagesUseCase.execute(Unit) } returns mockMessageList
        coEvery { saveMessagesUseCase.execute(any()) } just Runs

        chatViewModel.sendMessage("New message")

        coVerify { saveMessagesUseCase.execute(any()) }
        coVerify { getMessagesUseCase.execute(Unit) }
    }

    @Test
    fun `Given error occurs When fetchMessages is called Then error state should be updated`() = runTest {
        val errorMessage = "Fetch error"
        coEvery { getMessagesUseCase.execute(Unit) } throws RuntimeException(errorMessage)

        chatViewModel.fetchMessages()

        val result = chatViewModel.error.first()
        assertEquals(errorMessage, result)
    }

    @Test
    fun `Given loading state When fetchMessages is called Then isLoading should be updated`() = runTest {
        val mockMessages = listOf(
            Message(id = 1, userId = 2, content = "Test message", timestamp = 12345, attachments = emptyList())
        )
        val mockUsers = listOf(User(id = 2, name = "Test User", avatarId = "avatar"))
        val mockMessageList = MessageList(messages = mockMessages, users = mockUsers)

        coEvery { getMessagesUseCase.execute(Unit) } returns mockMessageList

        chatViewModel.fetchMessages()

        assertTrue(chatViewModel.isLoading.first())
        delay(500) // Wait for the delayBeforeLoading to complete
        assertEquals(false, chatViewModel.isLoading.first())
    }

    @Test
    fun `Given message is sent When sendMessage is called Then state should be reset`() = runTest {
        val mockMessages = listOf(
            Message(id = 1, userId = 2, content = "Test message", timestamp = 12345, attachments = emptyList())
        )
        val mockUsers = listOf(User(id = 2, name = "Test User", avatarId = "avatar"))
        val mockMessageList = MessageList(messages = mockMessages, users = mockUsers)

        coEvery { getMessagesUseCase.execute(Unit) } returns mockMessageList
        coEvery { saveMessagesUseCase.execute(any()) } just Runs

        chatViewModel.sendMessage("New message")

        coVerify { saveMessagesUseCase.execute(any()) }
        coVerify { getMessagesUseCase.execute(Unit) }

        assertEquals(1, chatViewModel.currentPage)
        assertEquals(false, chatViewModel.allMessagesLoaded)
        assertEquals(1, chatViewModel.messagesWithUsers.first().size)
    }
}

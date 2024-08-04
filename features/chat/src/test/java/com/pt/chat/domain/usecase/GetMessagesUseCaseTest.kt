package com.pt.chat.domain.usecase

import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.MessageList
import com.pt.chat.domain.useCase.GetMessagesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class GetMessagesUseCaseTest {

    private lateinit var repository: IChatRepository
    private lateinit var getMessagesUseCase: GetMessagesUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getMessagesUseCase = GetMessagesUseCase(repository)
    }

    @Test
    fun `Given repository returns data When execute is called Then it should return MessageList`() = runBlocking {
        val expectedMessageList = MessageList(users = listOf(), messages = listOf())
        coEvery { repository.getMessages() } returns expectedMessageList

        val result = getMessagesUseCase.execute(Unit)

        assertEquals(expectedMessageList, result)
        coVerify { repository.getMessages() }
    }

    @Test
    fun `Given repository throws exception When execute is called Then it should throw exception`() = runBlocking {
        val exception = RuntimeException("Repository error")
        coEvery { repository.getMessages() } throws exception

        val thrownException = assertThrows(RuntimeException::class.java) {
            runBlocking {
                getMessagesUseCase.execute(Unit)
            }
        }

        assertEquals("Repository error", thrownException.message)
        coVerify { repository.getMessages() }
    }
}

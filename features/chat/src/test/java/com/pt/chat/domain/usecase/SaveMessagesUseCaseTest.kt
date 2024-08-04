package com.pt.chat.domain.usecase

import com.pt.chat.data.mapper.toEntity
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.chat.mock.mockMessage
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class SaveMessagesUseCaseTest {

    private lateinit var repository: IChatRepository
    private lateinit var saveMessagesUseCase: SaveMessagesUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        saveMessagesUseCase = SaveMessagesUseCase(repository)
    }

    @Test
    fun `Given repository succeeds When execute is called Then it should save messages`() = runBlocking {
        val messages = listOf(mockMessage)
        val messageEntities = messages.map { it.toEntity() }

        coEvery { repository.saveMessages(messageEntities) } returns Unit

        saveMessagesUseCase.execute(messages)

        coVerify { repository.saveMessages(messageEntities) }
    }

    @Test
    fun `Given repository throws exception When execute is called Then it should throw exception`() = runBlocking {
        val messages = listOf(mockMessage)
        val messageEntities = messages.map { it.toEntity() }
        val exception = RuntimeException("Repository error")

        coEvery { repository.saveMessages(messageEntities) } throws exception

        val thrownException = assertThrows(RuntimeException::class.java) {
            runBlocking {
                saveMessagesUseCase.execute(messages)
            }
        }

        assertEquals("Repository error", thrownException.message)
        coVerify { repository.saveMessages(messageEntities) }
    }
}

package com.pt.chat.domain.usecase

import com.pt.chat.data.mapper.toEntity
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.useCase.SaveUsersUseCase
import com.pt.chat.mock.mockUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class SaveUsersUseCaseTest {

    private lateinit var repository: IChatRepository
    private lateinit var saveUsersUseCase: SaveUsersUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        saveUsersUseCase = SaveUsersUseCase(repository)
    }

    @Test
    fun `Given repository succeeds When execute is called Then it should save users`() = runBlocking {
        val users = listOf(mockUser)
        val userEntities = users.map { it.toEntity() }

        coEvery { repository.saveUsers(userEntities) } returns Unit

        saveUsersUseCase.execute(users)

        coVerify { repository.saveUsers(userEntities) }
    }

    @Test
    fun `Given repository throws exception When execute is called Then it should throw exception`() = runBlocking {
        val users = listOf(mockUser)
        val userEntities = users.map { it.toEntity() }
        val exception = RuntimeException("Repository error")

        coEvery { repository.saveUsers(userEntities) } throws exception

        val thrownException = assertThrows(RuntimeException::class.java) {
            runBlocking {
                saveUsersUseCase.execute(users)
            }
        }

        assertEquals("Repository error", thrownException.message)
        coVerify { repository.saveUsers(userEntities) }
    }
}

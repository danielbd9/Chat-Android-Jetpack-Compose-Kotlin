package com.pt.chat.domain.interactor

import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.domain.useCase.SaveAttachmentsUseCase
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.chat.domain.useCase.SaveUsersUseCase
import com.pt.chat.mock.mockMessageList
import com.pt.network.interceptor.NetworkException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class InitializeDataInteractorTest {

    private lateinit var fetchMessagesFromApiUseCase: GetMessagesUseCase
    private lateinit var saveMessagesUseCase: SaveMessagesUseCase
    private lateinit var saveUsersUseCase: SaveUsersUseCase
    private lateinit var saveAttachmentsUseCase: SaveAttachmentsUseCase
    private lateinit var initializeDataInteractor: InitializeDataInteractor

    @Before
    fun setUp() {
        fetchMessagesFromApiUseCase = mockk()
        saveMessagesUseCase = mockk()
        saveUsersUseCase = mockk()
        saveAttachmentsUseCase = mockk()
        initializeDataInteractor = InitializeDataInteractor(
            fetchMessagesFromApiUseCase,
            saveMessagesUseCase,
            saveUsersUseCase,
            saveAttachmentsUseCase
        )
    }

    @Test
    fun `Given all use cases succeed When execute is called Then it should complete successfully`() = runTest  {
        coEvery { fetchMessagesFromApiUseCase.execute(Unit) } returns mockMessageList
        coEvery { saveMessagesUseCase.execute(any()) } returns Unit
        coEvery { saveUsersUseCase.execute(any()) } returns Unit
        coEvery { saveAttachmentsUseCase.execute(any()) } returns Unit

        initializeDataInteractor.execute()

        coVerify { fetchMessagesFromApiUseCase.execute(Unit) }
        coVerify { saveMessagesUseCase.execute(any()) }
        coVerify { saveUsersUseCase.execute(any()) }
        coVerify { saveAttachmentsUseCase.execute(any()) }
    }

    @Test
    fun `Given fetchMessagesFromApiUseCase throws exception When execute is called Then it should throw ApiException`() = runTest  {
        val exception = NetworkException.ApiException("Fetch messages error")

        coEvery { fetchMessagesFromApiUseCase.execute(Unit) } throws exception

        val thrownException = assertThrows(NetworkException.ApiException::class.java) {
            runBlocking  {
                initializeDataInteractor.execute()
            }
        }

        assert(thrownException.message == "Fetch messages error")
    }

    @Test
    fun `Given saveMessagesUseCase throws exception When execute is called Then it should throw ApiException`() = runTest  {
        val exception = NetworkException.ApiException("Save messages error")

        coEvery { fetchMessagesFromApiUseCase.execute(Unit) } returns mockMessageList
        coEvery { saveMessagesUseCase.execute(any()) } throws exception

        val thrownException = assertThrows(NetworkException.ApiException::class.java) {
            runBlocking  {
                initializeDataInteractor.execute()
            }
        }

        assert(thrownException.message == "Save messages error")
    }

    @Test
    fun `Given fetchMessagesFromApiUseCase throws NetworkException When execute is called Then it should throw NetworkException`() = runTest  {
        val exception = NetworkException.BadRequestException("Bad request error")

        coEvery { fetchMessagesFromApiUseCase.execute(Unit) } throws exception

        val thrownException = assertThrows(NetworkException::class.java) {
            runBlocking  {
                initializeDataInteractor.execute()
            }
        }

        assert(thrownException.message == "Bad request error")
    }

    @Test
    fun `Given unexpected exception occurs When execute is called Then it should throw ApiException`() = runTest  {
        val exception = NetworkException.ApiException("Unexpected error")

        coEvery { fetchMessagesFromApiUseCase.execute(Unit) } throws exception

        val thrownException = assertThrows(NetworkException.ApiException::class.java) {
            runBlocking  {
                initializeDataInteractor.execute()
            }
        }

        assert(thrownException.message == "Unexpected error")
    }
}

package com.pt.chat.domain.usecase

import com.pt.chat.data.mapper.toEntity
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.useCase.SaveAttachmentsUseCase
import com.pt.chat.mock.mockAttachment
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class SaveAttachmentsUseCaseTest {

    private lateinit var repository: IChatRepository
    private lateinit var saveAttachmentsUseCase: SaveAttachmentsUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        saveAttachmentsUseCase = SaveAttachmentsUseCase(repository)
    }

    @Test
    fun `Given repository succeeds When execute is called Then it should save attachments`() = runBlocking {
        val messageId = 1
        val mockAttachment = mockAttachment
        val attachments = listOf(mockAttachment)
        val attachmentEntities = attachments.map { it.toEntity(messageId) }

        coEvery { repository.saveAttachments(attachmentEntities) } returns Unit

        saveAttachmentsUseCase.execute(Pair(messageId, attachments))

        coVerify { repository.saveAttachments(attachmentEntities) }
    }

    @Test
    fun `Given repository throws exception When execute is called Then it should throw exception`() = runBlocking {
        val messageId = 1
        val mockAttachment = mockAttachment
        val attachments = listOf(mockAttachment)
        val attachmentEntities = attachments.map { it.toEntity(messageId) }
        val exception = RuntimeException("Repository error")

        coEvery { repository.saveAttachments(attachmentEntities) } throws exception

        val thrownException = assertThrows(RuntimeException::class.java) {
            runBlocking {
                saveAttachmentsUseCase.execute(Pair(messageId, attachments))
            }
        }

        assertEquals("Repository error", thrownException.message)
        coVerify { repository.saveAttachments(attachmentEntities) }
    }
}

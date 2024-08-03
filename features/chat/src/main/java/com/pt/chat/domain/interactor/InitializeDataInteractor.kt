package com.pt.chat.domain.interactor

import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.domain.useCase.SaveAttachmentsUseCase
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.chat.domain.useCase.SaveUsersUseCase

class InitializeDataInteractor(
    private val fetchMessagesFromApiUseCase: GetMessagesUseCase,
    private val saveMessagesUseCase: SaveMessagesUseCase,
    private val saveUsersUseCase: SaveUsersUseCase,
    private val saveAttachmentsUseCase: SaveAttachmentsUseCase
) {
    suspend fun execute() {
        val messageList = fetchMessagesFromApiUseCase.execute(Unit)
        saveMessagesUseCase.execute(messageList.messages)
        saveUsersUseCase.execute(messageList.users)
        saveAttachmentsUseCase.execute(messageList.messages.flatMap { it.attachments.orEmpty() })
    }
}
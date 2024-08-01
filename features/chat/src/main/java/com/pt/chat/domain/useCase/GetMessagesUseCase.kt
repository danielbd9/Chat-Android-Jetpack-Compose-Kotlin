package com.pt.chat.domain.useCase

import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.Message

class GetMessagesUseCase(private val repository: IChatRepository) {
    suspend fun execute(): List<Message> {
        return repository.getMessages().messages
    }
}
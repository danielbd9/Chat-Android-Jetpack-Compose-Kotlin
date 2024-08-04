package com.pt.chat.domain.useCase

import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.MessageList
import com.pt.common.utils.BaseUseCase

class GetMessagesUseCase(private val repository: IChatRepository)
    : BaseUseCase<Unit, MessageList> {
    override suspend fun execute(params: Unit): MessageList {
        return repository.getMessages()
    }
}
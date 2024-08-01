package com.pt.chat.domain.interfaces

import com.pt.chat.domain.model.MessageList

interface IChatRepository {
    suspend fun getMessages(): MessageList
}
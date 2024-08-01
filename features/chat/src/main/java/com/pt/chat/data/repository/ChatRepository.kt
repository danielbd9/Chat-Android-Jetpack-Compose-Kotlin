package com.pt.chat.data.repository

import com.pt.chat.data.ApiService
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.model.MessageList


class ChatRepositoryImpl(private val apiService: ApiService) : IChatRepository {
    override suspend fun getMessages(): MessageList {
        return apiService.getMessages()
    }
}
package com.pt.chat.domain.interfaces

import com.pt.chat.domain.model.MessageList
import retrofit2.http.GET

interface  IChatApiService {

    @GET("conversation")
    suspend fun fetchMessagesFromApi(): MessageList
}
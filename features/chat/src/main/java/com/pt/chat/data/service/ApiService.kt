package com.pt.chat.data.service

import com.pt.chat.domain.model.MessageList
import retrofit2.http.GET

interface  ApiService {

    @GET("conversation")
    suspend fun fetchMessagesFromApi(): MessageList
}
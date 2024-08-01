package com.pt.chat.data

import com.pt.chat.domain.model.MessageList
import retrofit2.http.GET

interface  ApiService {

    @GET("conversation")
    suspend fun getMessages(): MessageList
}
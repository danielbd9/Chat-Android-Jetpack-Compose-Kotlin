package com.pt.chat.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageList(
    @SerialName("messages")
    val messages: List<Message>,
    @SerialName("users")
    val users: List<User>
)

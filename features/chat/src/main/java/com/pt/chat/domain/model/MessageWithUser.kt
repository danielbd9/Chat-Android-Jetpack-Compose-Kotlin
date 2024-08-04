package com.pt.chat.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageWithUser(
    @SerialName("messages")
    val messages: Message,
    @SerialName("users")
    val users: User
)

package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageWithUser(
    val messages: Message,
    val users: User
)

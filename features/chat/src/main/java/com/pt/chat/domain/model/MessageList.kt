package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MessageList(
    val messages: List<Message>
)

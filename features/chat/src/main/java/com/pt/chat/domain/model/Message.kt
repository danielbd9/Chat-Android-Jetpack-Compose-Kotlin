package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: Int,
    val userId: Int,
    val content: String,
    val timestamp: Long,
    val attachments: List<Attachment>?
)
package com.pt.chat.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("id")
    val id: Int,
    @SerialName("userId")
    val userId: Int,
    @SerialName("content")
    val content: String?,
    @SerialName("timestamp")
    val timestamp: Long = 0L,
    @SerialName("attachments")
    val attachments: List<Attachment>?
)
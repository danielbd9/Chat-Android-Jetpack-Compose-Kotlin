package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    val id: String,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)
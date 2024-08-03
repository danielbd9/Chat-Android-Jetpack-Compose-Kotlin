package com.pt.chat.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String?,
    val avatarId: String?
)
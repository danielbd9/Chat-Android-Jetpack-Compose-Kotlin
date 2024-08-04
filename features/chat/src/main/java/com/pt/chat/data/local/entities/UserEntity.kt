package com.pt.chat.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "users")
@Serializable
data class UserEntity(
    @PrimaryKey @SerialName("id") val id: Int,
    @SerialName("name") val name: String?,
    @SerialName("avatarId") val avatarId: String?
)
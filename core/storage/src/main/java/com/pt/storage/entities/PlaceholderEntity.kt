package com.pt.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "placeholder")
data class PlaceholderEntity(
    @PrimaryKey @SerialName("id") val id: Int = 0
)
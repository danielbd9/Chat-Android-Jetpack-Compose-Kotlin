package com.pt.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachments")
data class AttachmentEntity(
    @PrimaryKey val id: String,
    val messageId: Int,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?
)
package com.pt.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.MessageWithAttachmentsEntity

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(messages: List<MessageEntity>)

    @Transaction
    @Query("SELECT * FROM messages")
    suspend fun getMessagesWithAttachments(): List<MessageWithAttachmentsEntity>
}
package com.pt.chat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pt.chat.data.local.converters.Converters
import com.pt.chat.data.local.dao.AttachmentDao
import com.pt.chat.data.local.dao.MessageDao
import com.pt.chat.data.local.dao.UserDao
import com.pt.chat.data.local.entities.AttachmentEntity
import com.pt.chat.data.local.entities.MessageEntity
import com.pt.chat.data.local.entities.UserEntity

@Database(
    entities = [MessageEntity::class, UserEntity::class, AttachmentEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
    abstract fun attachmentDao(): AttachmentDao
}
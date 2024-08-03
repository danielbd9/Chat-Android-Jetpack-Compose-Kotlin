package com.pt.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity

@Database(entities = [MessageEntity::class, UserEntity::class, AttachmentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
    abstract fun attachmentDao(): AttachmentDao
}
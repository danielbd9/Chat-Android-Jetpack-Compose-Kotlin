package com.pt.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pt.database.Converters
import com.pt.database.entities.AttachmentEntity
import com.pt.database.entities.MessageEntity
import com.pt.database.entities.UserEntity

@Database(entities = [MessageEntity::class, UserEntity::class, AttachmentEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun userDao(): UserDao
    abstract fun attachmentDao(): AttachmentDao
}
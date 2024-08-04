package com.pt.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pt.storage.entities.PlaceholderEntity

@Database(entities = [PlaceholderEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

}
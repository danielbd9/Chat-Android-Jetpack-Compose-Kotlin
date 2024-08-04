package com.pt.chat.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pt.chat.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userEntities: List<UserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>
}
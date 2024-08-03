package com.pt.database.di

import androidx.room.Room
import com.pt.database.dao.AppDatabase
import org.koin.dsl.module


val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().messageDao() }
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().attachmentDao() }
}
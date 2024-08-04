package com.pt.storage.di

import android.content.Context
import androidx.room.Room
import com.pt.storage.database.AppDatabase
import org.koin.dsl.module

val storageModule = module {
    single { provideDatabase(get()) }
}

fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "app_database"
    ).fallbackToDestructiveMigration().build()
}
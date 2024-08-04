package com.pt

import android.app.Application
import com.pt.chat.di.chatModule
import com.pt.network.di.networkModule
import com.pt.storage.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                chatModule,
                networkModule,
                storageModule,
            )
        }
    }
}
package com.pt

import android.app.Application
import com.pt.chat.di.chatModule
import com.pt.core.di.coreModule
import com.pt.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                coreModule,
                networkModule,
                chatModule
            )
        }
    }
}
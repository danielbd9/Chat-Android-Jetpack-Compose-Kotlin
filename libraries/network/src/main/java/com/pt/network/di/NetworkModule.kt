package com.pt.network.di

import org.koin.dsl.module
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://private-edd460-7egendchallengeandroid.apiary-mock.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
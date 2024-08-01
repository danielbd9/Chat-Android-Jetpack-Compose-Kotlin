package com.pt.chat.di

import com.pt.chat.data.ApiService
import com.pt.chat.data.repository.ChatRepositoryImpl
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.presentation.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val chatModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }

    single<IChatRepository> { ChatRepositoryImpl(get()) }

    factory { GetMessagesUseCase(get()) }

    viewModel { ChatViewModel(get()) }
}
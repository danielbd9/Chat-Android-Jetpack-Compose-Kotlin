package com.pt.chat.di

import com.pt.chat.data.service.ApiService
import com.pt.chat.data.repository.ChatRepositoryImpl
import com.pt.chat.domain.interactor.InitializeDataInteractor
import com.pt.chat.domain.interfaces.IChatRepository
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.domain.useCase.SaveAttachmentsUseCase
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.chat.domain.useCase.SaveUsersUseCase
import com.pt.chat.presentation.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val chatModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }

    single<IChatRepository> { ChatRepositoryImpl(get(), get(), get(), get()) }

    factory { InitializeDataInteractor(get(), get(), get(), get()) }

    factory { GetMessagesUseCase(get()) }

    factory { SaveAttachmentsUseCase(get()) }

    factory { SaveMessagesUseCase(get()) }

    factory { SaveUsersUseCase(get()) }

    viewModel { ChatViewModel(get(), get(), get())}
}
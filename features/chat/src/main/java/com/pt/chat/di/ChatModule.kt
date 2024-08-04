package com.pt.chat.di

import android.content.Context
import androidx.room.Room
import com.pt.chat.data.local.ChatDatabase
import com.pt.chat.data.repository.ChatRepositoryImpl
import com.pt.chat.domain.interactor.InitializeDataInteractor
import com.pt.chat.domain.interfaces.IChatApiService
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
    single { get<Retrofit>().create(IChatApiService::class.java) }

    single<IChatRepository> { ChatRepositoryImpl(get(), get(), get(), get()) }

    factory { InitializeDataInteractor(get(), get(), get(), get()) }

    factory { GetMessagesUseCase(get()) }

    factory { SaveAttachmentsUseCase(get()) }

    factory { SaveMessagesUseCase(get()) }

    factory { SaveUsersUseCase(get()) }

    single {
        Room.databaseBuilder(
            get<Context>(),
            ChatDatabase::class.java,
            "chat_database"
        ).fallbackToDestructiveMigration().build()
    }

    single { get<ChatDatabase>().messageDao() }
    single { get<ChatDatabase>().userDao() }
    single { get<ChatDatabase>().attachmentDao() }

    viewModel { ChatViewModel(get(), get(), get()) }
}

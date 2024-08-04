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
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit

val chatModule = module {
    single {
        get<Retrofit> { parametersOf("https://private-edd460-7egendchallengeandroid.apiary-mock.com/") }.create(
            IChatApiService::class.java
        )
    }

    singleOf(::ChatRepositoryImpl) { bind<IChatRepository>() }

    factoryOf(::InitializeDataInteractor)

    factoryOf(::GetMessagesUseCase)

    factoryOf(::SaveAttachmentsUseCase)

    factoryOf(::SaveMessagesUseCase)

    factoryOf(::SaveUsersUseCase)

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

    viewModelOf(::ChatViewModel)
}
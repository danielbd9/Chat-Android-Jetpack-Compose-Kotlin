package com.pt.chat.presentation

import com.pt.chat.domain.model.MessageWithUser
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.core.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel(
    private val getMessagesUseCase: GetMessagesUseCase,
) : BaseViewModel() {

    private val _messagesWithUsers = MutableStateFlow<List<MessageWithUser>>(emptyList())
    val messagesWithUsers: StateFlow<List<MessageWithUser>> = _messagesWithUsers

    init {
        fetchMessagesWithUsers()
    }

    private fun fetchMessagesWithUsers() {
        doAsyncWork {
            val data = getMessagesUseCase.execute(Unit)
            _messagesWithUsers.value = data.messages.mapNotNull { message ->
                val user = data.users.find { it.id == message.userId }
                user?.let {
                    MessageWithUser(message, it)
                }
            }
        }
    }
}
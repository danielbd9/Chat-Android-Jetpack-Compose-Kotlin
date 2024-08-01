package com.pt.chat.presentation

import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.core.utils.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel(
    private val getMessagesUseCase: GetMessagesUseCase
) : BaseViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        fetchMessages()
    }

    fun fetchMessages() {
        doAsyncWork {
            val messageList = getMessagesUseCase.execute(Unit)
            _messages.value = messageList.messages
            _users.value = messageList.users
        }
    }
}

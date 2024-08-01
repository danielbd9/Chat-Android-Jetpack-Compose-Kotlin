package com.pt.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.chat.domain.useCase.GetMessagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val getMessagesUseCase: GetMessagesUseCase,
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    init {
        fetchMessages()
    }

    private fun fetchMessages() {
        viewModelScope.launch {
            try {
                val fetchedMessages = getMessagesUseCase.execute()
                _messages.value = fetchedMessages
            } catch (e: Exception) {
              val error = e.message
            }
        }
    }
}
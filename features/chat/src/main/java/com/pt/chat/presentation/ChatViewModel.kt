package com.pt.chat.presentation

import com.pt.chat.domain.interactor.InitializeDataInteractor
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.MessageWithUser
import com.pt.chat.domain.useCase.GetMessagesUseCase
import com.pt.chat.domain.useCase.SaveMessagesUseCase
import com.pt.common.utils.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val saveMessagesUseCase: SaveMessagesUseCase,
    private val initializeDataInteractor: InitializeDataInteractor
) : BaseViewModel() {

    private val _messagesWithUsers = MutableStateFlow<List<MessageWithUser>>(emptyList())
    val messagesWithUsers: StateFlow<List<MessageWithUser>> = _messagesWithUsers.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentPage = 0
    private val pageSize = 10
    private var allMessagesLoaded = false

    init {
        initializeData()
    }

    private fun initializeData() {
        doAsyncWork {
            initializeDataInteractor.execute()
            fetchMessages(reset = true)
        }
    }

    private fun fetchMessages(reset: Boolean = false) {
        if (_isLoading.value) return

        doAsyncWork {
            _isLoading.value = true

            val data = getMessagesUseCase.execute(Unit)
            val totalMessages = data.messages.sortedByDescending { it.timestamp }

            val paginatedMessages = if (reset) {
                currentPage = 0
                totalMessages.take(pageSize)
            } else {
                totalMessages.drop(currentPage * pageSize).take(pageSize)
            }

            val newMessagesWithUsers = paginatedMessages.mapNotNull { message ->
                val user = data.users.find { it.id == message.userId }
                user?.let {
                    MessageWithUser(message, it)
                }
            }

            _messagesWithUsers.value = if (reset) newMessagesWithUsers else _messagesWithUsers.value + newMessagesWithUsers

            if (newMessagesWithUsers.isNotEmpty()) {
                currentPage++
            } else {
                allMessagesLoaded = true
            }

            delayBeforeLoading(false)
        }
    }

    private suspend fun delayBeforeLoading(value: Boolean) {
        delay(500)
        _isLoading.value = value
    }

    fun loadMoreMessages() {
        if (!allMessagesLoaded && !_isLoading.value) {
            fetchMessages()
        }
    }

    fun sendMessage(content: String) {
        if (content.isNotBlank()) {
            doAsyncWork {
                val message = Message(
                    id = generateMessageId(),
                    userId = getLoggedInUserId(),
                    content = content,
                    timestamp = System.currentTimeMillis(),
                    attachments = emptyList()
                )
                saveMessagesUseCase.execute(listOf(message))
                currentPage = 0
                allMessagesLoaded = false
                _messagesWithUsers.value = emptyList()
                fetchMessages(reset = true)
            }
        }
    }

    private fun generateMessageId(): Int {
        return (messagesWithUsers.value.maxOfOrNull { it.messages.id } ?: 0) + 1
    }

    fun getLoggedInUserId(): Int {
        // Mock Id logged in user
        return 2
    }
}

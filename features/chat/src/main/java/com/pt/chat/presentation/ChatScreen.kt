package com.pt.chat.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pt.chat.domain.model.Message
import com.pt.core.utils.BaseErrorScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val messages by chatViewModel.messages.collectAsState()
    val error by chatViewModel.error.collectAsState()

    if (error != null) {
        BaseErrorScreen(
            errorMessage = error ?: "Unknown error",
            onRetry = { chatViewModel.fetchMessages() }
        )
    } else {
        LazyColumn(modifier = modifier) {
            items(messages) { message ->
                MessageItem(message)
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Text(text = message.content)
}

fun NavGraphBuilder.addChatScreen(navController: NavHostController) {
    composable(route = "chat") {
        ChatScreen(navController)
    }
}

package com.pt.chat.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.pt.chat.presentation.ui.components.MessageBubble
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChatScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val messagesWithUsers by chatViewModel.messagesWithUsers.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(messagesWithUsers) { messageWithUser ->
            MessageBubble(
                message = messageWithUser.message,
                user = messageWithUser.user,
                modifier = Modifier.fillMaxWidth(),
                isSent = messageWithUser.message.userId == 1 // Change user Logged
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

fun NavGraphBuilder.addChatScreen(navController: NavHostController) {
    composable(route = "chat") {
        ChatScreen(navController)
    }
}

@Preview
@Composable
fun PreviewChatScreen() {
    ChatScreen(navController = NavHostController(context = androidx.compose.ui.platform.LocalContext.current))
}
package com.pt.chat.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pt.chat.presentation.ChatViewModel
import com.pt.chat.presentation.ui.components.MessageBubble
import com.pt.components.ui.LoadingIndicator
import com.pt.components.mapper.getPrimaryColor
import com.pt.components.mapper.getSecondaryColor
import com.pt.components.ui.ErrorMessage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = koinViewModel()
) {
    val messagesWithUsers by chatViewModel.messagesWithUsers.collectAsState()
    val isLoading by chatViewModel.isLoading.collectAsState()
    val error by chatViewModel.error.collectAsState()
    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .collect { visibleItemsInfo ->
                val lastVisibleItemIndex = visibleItemsInfo.lastOrNull()?.index ?: 0
                if (lastVisibleItemIndex == messagesWithUsers.size - 1 && !isLoading) {
                    chatViewModel.loadMoreMessages()
                }
            }
    }

    if(error.isNullOrEmpty()){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .background(getSecondaryColor())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    LazyColumn(
                        state = listState,
                        reverseLayout = true,
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(messagesWithUsers) { messageWithUser ->
                            MessageBubble(
                                message = messageWithUser.messages,
                                user = messageWithUser.users,
                                navController = navController,
                                modifier = Modifier.fillMaxWidth(),
                                isSent = messageWithUser.messages.userId == chatViewModel.getLoggedInUserId()
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }

                    if (isLoading) {
                        LoadingIndicator()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(24.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFF1F1F1), shape = RoundedCornerShape(24.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        maxLines = 5,
                        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
                    ) { innerTextField ->
                        if (messageText.text.isEmpty()) {
                            Text(
                                text = "Type a message",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                    IconButton(
                        onClick = {
                            if (messageText.text.isNotBlank()) {
                                chatViewModel.sendMessage(messageText.text)
                                messageText = TextFieldValue("")
                                coroutineScope.launch {
                                    listState.animateScrollToItem(0, scrollOffset = 10)
                                }
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send",
                            tint = getPrimaryColor()
                        )
                    }
                }
            }
        }
    }
    else{
        ErrorMessage("Sorry, something went wrong! \n Try again later.")
    }
}
package com.pt.chat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.core.utils.theme.getPrimaryColor
import com.pt.core.utils.theme.getUserColor
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun getInitials(name: String): String {
    return name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
}

@Composable
fun MessageBubble(
    message: Message,
    user: User,
    navController: NavController,
    modifier: Modifier = Modifier,
    isSent: Boolean = false
) {
    Row(
        modifier = modifier.padding(8.dp),
        horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start
    ) {
        if (!isSent) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(getUserColor(user.id), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getInitials(user.name.orEmpty()),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(
                    color = if (isSent) Color.Black else Color.White,
                    shape = if (isSent) {
                        RoundedCornerShape(8.dp).copy(bottomEnd = CornerSize(0))
                    } else {
                        RoundedCornerShape(8.dp).copy(bottomStart = CornerSize(0))
                    }
                )
                .clickable {
                    if (message.attachments?.isNotEmpty() == true) {
                        val encodedUrl = URLEncoder.encode(message.attachments[0].url, StandardCharsets.UTF_8.toString())
                        navController.navigate("attachment/$encodedUrl")
                    }
                }
                .padding(8.dp)
        ) {
            Column {
                if (!isSent) {
                    Text(
                        text = user.name.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = getPrimaryColor()
                    )
                }
                Text(
                    text = message.content.orEmpty(),
                    color = if (isSent) Color.White else Color.Black,
                )

                if (message.attachments?.isNotEmpty() == true) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Anexo",
                        modifier = Modifier
                            .align(Alignment.End)
                            .size(20.dp)
                            .padding(top = 4.dp),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessageBubblePreview() {
    val user = User(id = 1, name = "John Doe", avatarId = "https://via.placeholder.com/40")
    val message = Message(
        id = 1,
        userId = 1,
        content = "Hello, this is a test message with an attachment.",
        timestamp = System.currentTimeMillis(),
        attachments = listOf(
            Attachment(id = "1", title = "Attachment", url = "https://via.placeholder.com/600/92c952", thumbnailUrl = "https://via.placeholder.com/40")
        )
    )
    Column {
        MessageBubble(
            message = message,
            user = user,
            navController = NavController(LocalContext.current),
            isSent = false
        )
        MessageBubble(
            message = message,
            user = user,
            navController = NavController(LocalContext.current),
            isSent = true
        )
    }
}

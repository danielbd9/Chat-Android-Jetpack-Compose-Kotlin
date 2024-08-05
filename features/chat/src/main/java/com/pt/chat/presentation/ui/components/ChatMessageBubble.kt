package com.pt.chat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.pt.chat.R
import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import com.pt.components.dimens.Dimens
import com.pt.components.mapper.getPrimaryColor
import com.pt.components.mapper.getSecondaryColor
import com.pt.components.mapper.getUserColor
import com.pt.components.ui.PlaceholderComponent
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
        modifier = modifier.padding(Dimens.mediumPadding),
        horizontalArrangement = if (isSent) Arrangement.End else Arrangement.Start
    ) {
        if (!isSent) {
            Box(
                modifier = Modifier
                    .size(Dimens.avatarSize)
                    .align(Alignment.Bottom)
                    .shadow(Dimens.elevation, CircleShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(getUserColor(user.id), getUserColor(user.id))
                        ),
                        shape = CircleShape
                    ),
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
                .padding(horizontal = Dimens.mediumPadding)
                .shadow(Dimens.elevation, shape = if (isSent) {
                    RoundedCornerShape(Dimens.messageBubbleCornerRadius).copy(bottomEnd = CornerSize(0))
                } else {
                    RoundedCornerShape(Dimens.messageBubbleCornerRadius).copy(bottomStart = CornerSize(0))
                })
                .background(
                    color = if (isSent) Color.Black else Color.White,
                    shape = if (isSent) {
                        RoundedCornerShape(Dimens.messageBubbleCornerRadius).copy(bottomEnd = CornerSize(0))
                    } else {
                        RoundedCornerShape(Dimens.messageBubbleCornerRadius).copy(bottomStart = CornerSize(0))
                    }
                )
                .padding(Dimens.mediumPadding)
        ) {
            Column {
                if (!isSent) {
                    Text(
                        text = user.name.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        color = getPrimaryColor(),
                        modifier = Modifier.padding(bottom = Dimens.smallPadding)
                    )
                }
                if (message.attachments?.isNotEmpty() == true) {
                    SubcomposeAsyncImage(
                        model = message.attachments[0].thumbnailUrl,
                        contentDescription = stringResource(id = R.string.chat_attachment_thumbnail_content_description),
                        contentScale = ContentScale.FillBounds,
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(Dimens.attachmentLoadingHeight)
                                    .clip(RoundedCornerShape(Dimens.messageBubbleCornerRadius))
                                    .background(getSecondaryColor()),
                                contentAlignment = Alignment.Center
                            ) {
                                PlaceholderComponent()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimens.attachmentThumbnailHeight)
                            .clip(RoundedCornerShape(Dimens.messageBubbleCornerRadius))
                            .background(getSecondaryColor())
                            .clickable {
                                val encodedUrl = URLEncoder.encode(
                                    message.attachments[0].url,
                                    StandardCharsets.UTF_8.toString()
                                )
                                val encodedTitle = URLEncoder.encode(
                                    message.attachments[0].title,
                                    StandardCharsets.UTF_8.toString()
                                )
                                navController.navigate("attachment/$encodedUrl/$encodedTitle")
                            }
                    )
                }
                Text(
                    text = message.content.orEmpty(),
                    color = if (isSent) Color.White else Color.Black,
                )
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
        content = stringResource(id = R.string.chat_test_message_content),
        timestamp = System.currentTimeMillis(),
        attachments = listOf(
            Attachment(id = "1", title = stringResource(id = R.string.chat_attachment_title), url = "https://via.placeholder.com/600/92c952", thumbnailUrl = "https://via.placeholder.com/40")
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

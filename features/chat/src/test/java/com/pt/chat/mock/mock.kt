package com.pt.chat.mock

import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.MessageList
import com.pt.chat.domain.model.User
import io.mockk.every
import io.mockk.mockk

val mockAttachment = mockk<Attachment> {
    every { id } returns "a4c3d2a1-72ee-48d9-893b-82d1c22f1f81"
    every { title } returns "accusamus ea aliquid et amet sequi nemo"
    every { url } returns "https://via.placeholder.com/600/56a8c2"
    every { thumbnailUrl } returns "https://via.placeholder.com/150/56a8c2"
}

val mockMessage = mockk<Message> {
    every { id } returns 1
    every { userId } returns 123
    every { content } returns "message content"
    every { timestamp } returns 1627849923000L
    every { attachments } returns listOf(mockAttachment)
}

val mockUser = mockk<User> {
    every { id } returns 1
    every { name } returns "John Doe"
    every { avatarId } returns "JD"
}


val mockMessageList = mockk<MessageList> {
    every { messages } returns listOf(mockMessage)
    every { users } returns listOf(mockUser)
}
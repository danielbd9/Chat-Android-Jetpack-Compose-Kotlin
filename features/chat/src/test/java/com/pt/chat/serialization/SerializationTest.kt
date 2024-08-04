package com.pt.chat.serialization

import com.pt.chat.domain.model.Attachment
import com.pt.chat.domain.model.Message
import com.pt.chat.domain.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class SerializationTest {

    private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

    @Test
    fun `serialize Message to JSON`() {
        val message = Message(
            id = 1,
            userId = 2,
            content = "Test message",
            timestamp = 1234567890,
            attachments = listOf(
                Attachment(
                    id = "1",
                    title = "Test Attachment",
                    url = "http://example.com",
                    thumbnailUrl = "http://example.com/thumb"
                )
            )
        )

        val jsonString = json.encodeToString(message)
        println(jsonString)

        val expectedJson = """
            {
                "id": 1,
                "userId": 2,
                "content": "Test message",
                "timestamp": 1234567890,
                "attachments": [
                    {
                        "id": "1",
                        "title": "Test Attachment",
                        "url": "http://example.com",
                        "thumbnailUrl": "http://example.com/thumb"
                    }
                ]
            }
        """.trimIndent()

        assertEquals(expectedJson, jsonString)
    }

    @Test
    fun `deserialize JSON to Message`() {
        val jsonString = """
            {
                "id": 1,
                "userId": 2,
                "content": "Test message",
                "timestamp": 1234567890,
                "attachments": [
                    {
                        "id": "1",
                        "title": "Test Attachment",
                        "url": "http://example.com",
                        "thumbnailUrl": "http://example.com/thumb"
                    }
                ]
            }
        """.trimIndent()

        val message = json.decodeFromString<Message>(jsonString)
        println(message)

        val expectedMessage = Message(
            id = 1,
            userId = 2,
            content = "Test message",
            timestamp = 1234567890,
            attachments = listOf(
                Attachment(
                    id = "1",
                    title = "Test Attachment",
                    url = "http://example.com",
                    thumbnailUrl = "http://example.com/thumb"
                )
            )
        )

        assertEquals(expectedMessage, message)
    }

    @Test
    fun `serialize User to JSON`() {
        val user = User(
            id = 2,
            name = "Test User",
            avatarId = "avatar"
        )

        val jsonString = json.encodeToString(user)
        println(jsonString)

        val expectedJson = """
            {
                "id": 2,
                "name": "Test User",
                "avatarId": "avatar"
            }
        """.trimIndent()

        assertEquals(expectedJson, jsonString)
    }

    @Test
    fun `deserialize JSON to User`() {
        val jsonString = """
            {
                "id": 2,
                "name": "Test User",
                "avatarId": "avatar"
            }
        """.trimIndent()

        val user = json.decodeFromString<User>(jsonString)
        println(user)

        val expectedUser = User(
            id = 2,
            name = "Test User",
            avatarId = "avatar"
        )

        assertEquals(expectedUser, user)
    }

    @Test
    fun `serialize Attachment to JSON`() {
        val attachment = Attachment(
            id = "1",
            title = "Test Attachment",
            url = "http://example.com",
            thumbnailUrl = "http://example.com/thumb"
        )

        val jsonString = json.encodeToString(attachment)
        println(jsonString)

        val expectedJson = """
            {
                "id": "1",
                "title": "Test Attachment",
                "url": "http://example.com",
                "thumbnailUrl": "http://example.com/thumb"
            }
        """.trimIndent()

        assertEquals(expectedJson, jsonString)
    }

    @Test
    fun `deserialize JSON to Attachment`() {
        val jsonString = """
            {
                "id": "1",
                "title": "Test Attachment",
                "url": "http://example.com",
                "thumbnailUrl": "http://example.com/thumb"
            }
        """.trimIndent()

        val attachment = json.decodeFromString<Attachment>(jsonString)
        println(attachment)

        val expectedAttachment = Attachment(
            id = "1",
            title = "Test Attachment",
            url = "http://example.com",
            thumbnailUrl = "http://example.com/thumb"
        )

        assertEquals(expectedAttachment, attachment)
    }
}

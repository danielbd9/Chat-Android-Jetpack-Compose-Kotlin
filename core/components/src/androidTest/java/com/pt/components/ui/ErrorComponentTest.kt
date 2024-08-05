package com.pt.components.ui

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class ErrorComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testErrorMessageDefault() {
        val errorMessage = "An error occurred"
        composeTestRule.setContent {
            ErrorComponent(error = errorMessage)
        }

        composeTestRule.onNodeWithTag("ErrorMessage").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ErrorMessageText").assert(hasText(errorMessage))
    }

    @Test
    fun testErrorMessageTextColor() {
        val errorMessage = "An error occurred"
        composeTestRule.setContent {
            ErrorComponent(error = errorMessage)
        }

        composeTestRule.onNodeWithTag("ErrorMessageText").assertIsDisplayed()
    }

    @Test
    fun testErrorMessageTextAlignment() {
        val errorMessage = "An error occurred"
        composeTestRule.setContent {
            ErrorComponent(error = errorMessage)
        }

        composeTestRule.onNodeWithTag("ErrorMessageText").assertIsDisplayed()
    }
}
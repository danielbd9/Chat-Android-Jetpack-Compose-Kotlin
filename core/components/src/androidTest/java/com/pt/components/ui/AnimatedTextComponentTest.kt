package com.pt.components.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class AnimatedTextComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAnimatedTextComponentDefault() {
        composeTestRule.setContent {
            AnimatedTextComponent()
        }

        composeTestRule.onNodeWithTag("AnimatedTextComponent").assertIsDisplayed()
    }

    @Test
    fun testAnimatedTextComponentCharAnimation() {
        composeTestRule.setContent {
            AnimatedTextComponent()
        }

        composeTestRule.waitUntil(timeoutMillis = 1000) {
            composeTestRule.onAllNodes(hasText("L")).fetchSemanticsNodes().isNotEmpty()
        }

        composeTestRule.waitUntil(timeoutMillis = 2000) {
            composeTestRule.onAllNodes(hasText("LE")).fetchSemanticsNodes().isNotEmpty()
        }
    }
}
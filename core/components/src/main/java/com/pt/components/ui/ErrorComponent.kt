package com.pt.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import com.pt.components.mapper.getPrimaryColor

@Composable
fun ErrorComponent(error: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .testTag("ErrorMessage"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = getPrimaryColor(),
            textAlign = TextAlign.Center,
            modifier = Modifier.testTag("ErrorMessageText")
        )
    }
}
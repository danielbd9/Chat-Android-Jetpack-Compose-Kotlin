package com.pt.components.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pt.components.mapper.getPrimaryColor
import kotlinx.coroutines.delay


@Composable
fun AnimatedTextComponent() {
    val words = listOf("LET'S", "TALK...")
    var currentWordIndex by remember { mutableIntStateOf(0) }
    var currentCharIndex by remember { mutableIntStateOf(0) }
    val currentWord = words[currentWordIndex]

    LaunchedEffect(key1 = currentWordIndex) {
        delay(2000)
        currentWordIndex = (currentWordIndex + 1) % words.size
        currentCharIndex = 0
    }

    LaunchedEffect(key1 = currentCharIndex) {
        if (currentCharIndex < currentWord.length) {
            delay(80)
            currentCharIndex++
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 130.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            Text(
                text = currentWord.take(currentCharIndex),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = getPrimaryColor()
            )
        }
    }
}
package com.pt.core.utils.theme

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingIndicator(
    totalTime: Long = 10,
    color: Color = Color.White,
    onTimerChange: (Long) -> Unit = {}
) {
    var currentTime by remember { mutableLongStateOf(totalTime) }
    var progress by remember { mutableFloatStateOf(1f) }

    val progressAnimate by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(
            durationMillis = 3000,
            delayMillis = 10,
            easing = LinearOutSlowInEasing
        ), label = ""
    )

    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0) {
            delay(50L)
            currentTime--
            progress = currentTime / totalTime.toFloat()
            onTimerChange(currentTime)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progressAnimate },
            modifier = Modifier.size(60.dp),
            color = color,
            strokeWidth = 10.dp,
            strokeCap = StrokeCap.Round,
        )
    }
}
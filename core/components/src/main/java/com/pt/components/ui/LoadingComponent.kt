package com.pt.components.ui

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pt.components.mapper.getPrimaryColor

@Composable
fun LoadingIndicator(
    color: Color = getPrimaryColor(),
    alignment: Alignment = Alignment.BottomCenter
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scale1 by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val scale2 by infiniteTransition.animateFloat(
        initialValue = 1.5f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearOutSlowInEasing, delayMillis = 200),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val scale3 by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearOutSlowInEasing, delayMillis = 400),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
        contentAlignment = alignment
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .scale(scale1)
                    .background(color, shape = androidx.compose.foundation.shape.CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .scale(scale2)
                    .background(color, shape = androidx.compose.foundation.shape.CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .scale(scale3)
                    .background(color, shape = androidx.compose.foundation.shape.CircleShape)
            )
        }
    }
}

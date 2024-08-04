package com.pt.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.pt.components.mapper.getSecondaryColor

@Composable
fun PlaceholderComponent(
    color: Color = getSecondaryColor(),
    alignment: Alignment = Alignment.Center,
    imageVector: ImageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = alignment
    ) {
//        Icon(
//            imageVector = imageVector,
//            contentDescription = null,
//            tint = color,
//            modifier = Modifier.size(48.dp)
//        )

        Text(text = "Loading...")
    }
}
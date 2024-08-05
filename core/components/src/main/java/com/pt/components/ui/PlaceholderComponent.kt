package com.pt.components.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pt.components.R
import com.pt.components.mapper.getSecondaryColor

@Composable
fun PlaceholderComponent(
    color: Color = getSecondaryColor(),
    alignment: Alignment = Alignment.Center,
    imageRes: Int = R.drawable.image
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color),
        contentAlignment = alignment
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageRes)
                .build()
        )

       Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
    }
}

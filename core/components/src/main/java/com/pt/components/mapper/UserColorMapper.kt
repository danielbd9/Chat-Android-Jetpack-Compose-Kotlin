package com.pt.components.mapper

import androidx.compose.ui.graphics.Color
import com.pt.components.theme.blue
import com.pt.components.theme.cyan
import com.pt.components.theme.darkGray
import com.pt.components.theme.gray
import com.pt.components.theme.green
import com.pt.components.theme.lightGray
import com.pt.components.theme.lightGreen
import com.pt.components.theme.lightOrange
import com.pt.components.theme.orange
import com.pt.components.theme.pink
import com.pt.components.theme.purple
import com.pt.components.theme.yellow

fun getUserColor(userId: Int): Color {
    val colors = listOf(
        cyan,
        lightGreen,
        green,
        blue,
        purple,
        darkGray,
        yellow,
        lightOrange,
        pink,
        lightGray
    )
    return colors[userId % colors.size]
}

fun getPrimaryColor(): Color {
   return orange
}

fun getSecondaryColor(): Color {
    return gray
}
package com.pt.core.utils.theme

import androidx.compose.ui.graphics.Color

fun getUserColor(userId: Int): Color {
    val colors = listOf(
        Color(0xFF1ABC9C),
        Color(0xFF2ECC71),
        Color(0xFF3498DB),
        Color(0xFF9B59B6),
        Color(0xFF34495E),
        Color(0xFFF1C40F),
        Color(0xFFE67E22),
        Color(0xFFE74C3C),
        Color(0xFFf9b9fc),
        Color(0xFF07aa28)
    )
    return colors[userId % colors.size]
}

fun getPrimaryColor(): Color {
   return Color( 0xFFfa4536)
}

fun getSecondaryColor(): Color {
    return Color(0xFFfcbeb9)
}

fun getLightGrayColor(): Color {
    return Color(0xFFededed)
}

fun getDarkGrayColor(): Color {
    return Color(0xFF8b8a8a)
}
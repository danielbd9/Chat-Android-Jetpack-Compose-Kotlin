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
        Color(0xFFECF0F1),
        Color(0xFF95A5A6)
    )
    return colors[userId % colors.size]
}

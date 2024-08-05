package com.pt.components.mapper

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
import org.junit.Assert.assertEquals
import org.junit.Test

class UserColorMapperTest {

    @Test
    fun testGetUserColorWithinRange() {
        assertEquals(cyan, getUserColor(0))
        assertEquals(lightGreen, getUserColor(1))
        assertEquals(green, getUserColor(2))
        assertEquals(blue, getUserColor(3))
        assertEquals(purple, getUserColor(4))
        assertEquals(darkGray, getUserColor(5))
        assertEquals(yellow, getUserColor(6))
        assertEquals(lightOrange, getUserColor(7))
        assertEquals(pink, getUserColor(8))
        assertEquals(lightGray, getUserColor(9))
    }

    @Test
    fun testGetUserColorOutOfRange() {
        assertEquals(cyan, getUserColor(10))
        assertEquals(lightGreen, getUserColor(11))
        assertEquals(green, getUserColor(12))
    }

    @Test
    fun testGetUserColorNegative() {
        assertEquals(lightGray, getUserColor(-1))
        assertEquals(pink, getUserColor(-2))
        assertEquals(lightOrange, getUserColor(-3))
    }

    @Test
    fun testGetPrimaryColor() {
        assertEquals(orange, getPrimaryColor())
    }

    @Test
    fun testGetSecondaryColor() {
        assertEquals(gray, getSecondaryColor())
    }
}
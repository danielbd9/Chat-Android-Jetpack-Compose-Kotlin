package com.pt.chat.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun MainScreen(navController: NavHostController,
               modifier: Modifier = Modifier) {
    Text(text = "This is the Main Screen Navigation")
}

fun NavGraphBuilder.addMainScreen(navController: NavHostController) {
    composable(route = "main") {
        MainScreen(navController)
    }
}
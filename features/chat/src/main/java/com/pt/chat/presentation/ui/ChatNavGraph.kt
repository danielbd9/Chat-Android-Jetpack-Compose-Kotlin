package com.pt.chat.presentation.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pt.chat.presentation.ui.screens.AttachmentScreen
import com.pt.chat.presentation.ui.screens.ChatScreen

fun NavGraphBuilder.addChatScreen(navController: NavHostController) {
    composable(route = "chat") {
        ChatScreen(navController)
    }
}

fun NavGraphBuilder.addAttachmentScreen(navController: NavHostController) {
    composable(
        route = "attachment/{attachmentUrl}",
        arguments = listOf(navArgument("attachmentUrl") { type = NavType.StringType })
    ) { backStackEntry ->
        val attachmentUrl = backStackEntry.arguments?.getString("attachmentUrl")
        AttachmentScreen(attachmentUrl = attachmentUrl) {
            navController.popBackStack()
        }
    }
}
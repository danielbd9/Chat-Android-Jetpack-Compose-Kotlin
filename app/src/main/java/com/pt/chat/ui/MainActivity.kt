package com.pt.chat.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pt.chat.presentation.ui.addAttachmentScreen
import com.pt.chat.presentation.ui.addChatScreen
import com.pt.chat.ui.splash.screen.SplashScreen
import com.pt.components.theme.ChatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    val statusBarColor = Color.Black

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = statusBarColor,
            darkIcons = useDarkIcons
        )
    }

    NavHost(
        navController = navController,
        startDestination = "splash",
    ) {
        addSplashScreen(navController)
        addChatScreen(navController)
        addAttachmentScreen(navController)
    }
}

fun NavGraphBuilder.addSplashScreen(navController: NavHostController) {
    composable(route = "splash") {
        SplashScreen(navController)
    }
}
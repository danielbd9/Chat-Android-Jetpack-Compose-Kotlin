package com.pt.chat.ui.splash.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pt.components.ui.AnimatedTextComponent
import com.pt.components.dimens.Dimens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("chat") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(Dimens.splashAnimationSize),
            resId = com.pt.components.R.raw.splash_animation,
            iterations = LottieConstants.IterateForever,
            speed = 1.0f
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Dimens.splashBottomPadding),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedTextComponent()
        }
    }
}

@Composable
fun LottieAnimation(
    modifier: Modifier = Modifier,
    resId: Int,
    iterations: Int = LottieConstants.IterateForever,
    speed: Float = 1f
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations,
        speed = speed
    )

    LottieAnimation(
        composition,
        progress,
        modifier
    )
}

package com.example.cafebrown.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextHeadlineLargeOnSecondary
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun SplashScreen(
//    splashViewModel: SplashViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_coffee))

    LaunchedEffect(Unit) {
        delay(SplashWaitTime)
//        if (splashViewModel.hasUserData.value){
//            onNavigateToHome()
//        }else{
        onNavigateToLogin()
//        }
    }

    MainColumn() {
        Box(){
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
            )

            TextHeadlineLargeOnSecondary(text = stringResource(id = R.string.app_name), modifier = Modifier.padding(32.dp).align(
                Alignment.BottomCenter))

        }

    }
}


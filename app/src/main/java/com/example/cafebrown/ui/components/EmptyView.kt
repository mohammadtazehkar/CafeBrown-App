package com.example.cafebrown.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cafebrown.R

@Composable
fun EmptyView(
    text: String
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_empty))

    CardColumnMediumCorner {

        LottieAnimation(
//        modifier = modifier,
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextBodyMedium(text = text, modifier = Modifier.padding(32.dp))
        }

    }

}
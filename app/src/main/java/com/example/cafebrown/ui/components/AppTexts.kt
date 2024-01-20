package com.example.cafebrown.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection

//region Display
@Composable
fun TextDisplaySmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextDisplaySmallPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayMediumPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextDisplaySmallOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayMediumOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayLargeOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextDisplaySmallOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displaySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayMediumOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextDisplayLargeOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
//endregion

//region Headline
@Composable
fun TextHeadlineSmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextHeadlineSmallPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineMediumPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextHeadlineSmallOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineMediumOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineLargeOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextHeadlineSmallOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineMediumOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextHeadlineLargeOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
//endregion

//region Title
@Composable
fun TextTitleSmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextTitleSmallPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleMediumPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextTitleSmallOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleMediumOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleLargeOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextTitleSmallOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleMediumOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextTitleLargeOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextTitleMediumError(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.error,
        fontWeight = fontWeight,
        modifier = modifier
    )
}@Composable
fun TextTitleSmallError(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.error,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
//endregion

//region Body
@Composable
fun TextBodySmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextBodySmallPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyMediumPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextBodySmallOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyMediumOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyLargeOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextBodySmallOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodySmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyMediumOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextBodyLargeOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
//endregion

//region Label
@Composable
fun TextLabelSmall(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelMedium(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelLarge(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextLabelSmallPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelMediumPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelLargePrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextLabelSmallOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelMediumOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelLargeOnPrimary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextLabelSmallOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelMediumOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelMedium,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}
@Composable
fun TextLabelLargeOnSecondary(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.onSecondary,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun TextLabelSmallError(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: FontWeight? = null
){
    Text(
        text = text ,
        style = MaterialTheme.typography.labelSmall,
        textAlign = textAlign,
        color = MaterialTheme.colorScheme.error,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

//endregion
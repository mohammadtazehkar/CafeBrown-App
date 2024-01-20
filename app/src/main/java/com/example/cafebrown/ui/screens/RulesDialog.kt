package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.CardColumnMediumCorner
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.SecondaryButton
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallOnSecondary

@Composable
fun RulesDialog(
    isFromAboutUs: Boolean = false,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = isFromAboutUs,
            dismissOnClickOutside = isFromAboutUs
        )
    ) {
        CardColumnMediumCorner(columnModifier = Modifier.verticalScroll(rememberScrollState())) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
            {
                TextTitleMedium(
                    text = stringResource(id = R.string.app_rules),
                    textAlign = TextAlign.Justify
                )
            }
            if (!isFromAboutUs)
                PrimaryButton(
                    text = stringResource(id = R.string.accept_rules),
                    onClick = onConfirmation
                )
        }
    }
}
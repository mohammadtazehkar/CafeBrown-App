package com.example.cafebrown.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.cafebrown.utils.ClickHelper

@Composable
fun AppSnackBar(
    snackBarData: SnackbarData,
    isError: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.background,
) {
    Snackbar(containerColor = containerColor) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isError) {
                    TextLabelSmallError(text = snackBarData.visuals.message)
                }else{
                    TextLabelSmallPrimary(text = snackBarData.visuals.message)
                }
                Spacer(Modifier.weight(1f))

                snackBarData.visuals.actionLabel?.let { actionLabel ->
                    TextButton(
                        onClick = {
                            ClickHelper.getInstance().clickOnce { snackBarData.performAction() }
                        }) {
                        TextLabelSmallPrimary(text = actionLabel)
                    }
                }
            }
        }
    }
}

package com.example.cafebrown.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.CardColumnMediumCorner
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.TextLabelSmall
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.theme.AppTheme

@Composable
fun ComplaintDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onChangeComplaint: (String) -> Unit,
    complaintText: String
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        CardColumnMediumCorner(columnModifier = Modifier.verticalScroll(rememberScrollState())) {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextTitleMedium(
                    text = stringResource(id = R.string.please_send_us_complaint),
                    modifier = Modifier.padding(8.dp)
                )
            }
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                value = complaintText,
                onValueChange = onChangeComplaint,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                placeholder = {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        TextLabelSmall(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.please_enter_your_complaint),
                            textAlign = TextAlign.Start
                        )
                    }
                })
            PrimaryButton(text = stringResource(id = R.string.submit)) {
                onConfirmation()
            }
//            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewComplaintDialog() {
    AppTheme {
        ComplaintDialog({}, {}, {}, "")
    }
}
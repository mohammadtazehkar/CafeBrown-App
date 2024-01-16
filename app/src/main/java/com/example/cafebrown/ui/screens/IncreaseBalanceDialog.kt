package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.CardColumnMediumCorner
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.TextLabelSmall
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.theme.AppTheme

@Composable
fun IncreaseBalanceDialog(
    balance: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    onChangeBalance: (String) -> Unit,
    increaseBalance: String
) {
    Dialog(
        onDismissRequest = { onDismissRequest() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        CardColumnMediumCorner(columnModifier = Modifier.verticalScroll(rememberScrollState())) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.background(MaterialTheme.colorScheme.background)
//            )
//            {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.mipmap.ic_transaction),
                contentDescription = "increase balance logo"
            )
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextTitleMedium(
                    text = " ${stringResource(id = R.string.your_wallet_balance_is)} ${balance} ${
                        stringResource(
                            id = R.string.toman
                        )
                    }", modifier = Modifier.padding(8.dp)
                )
            }
            TextField(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                value = increaseBalance,
                onValueChange = onChangeBalance,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                textStyle = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                placeholder = {
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        TextLabelSmall(
                            text = stringResource(id = R.string.please_enter_increase_amount)
                        )
                    }
                })
            PrimaryButton(text = stringResource(id = R.string.online_payment)) {
                onConfirmation()
            }
//            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewDialog() {
    AppTheme {
        IncreaseBalanceDialog("27000", {}, {}, {}, "3500")
    }
}
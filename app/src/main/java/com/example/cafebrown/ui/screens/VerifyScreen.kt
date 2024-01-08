package com.example.cafebrown.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.VerifyEvent
import com.example.cafebrown.presentation.viewmodels.VerifyViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.KeyboardHandler
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.SecondaryButton
import com.example.cafebrown.ui.components.TextBodyMedium
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallPrimary
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VerifyScreen(
    verifyViewModel : VerifyViewModel = viewModel(),
    onNavigateToProfile : () -> Unit
){
    val context = LocalContext.current
    val verifyState = verifyViewModel.verifyState.value
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        verifyViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

    if (verifyState.isRulesDialogVisible) {
        RulesDialog(
            onDismissRequest = {
                verifyViewModel.onEvent(VerifyEvent.UpdateRulesDialogVisibility(false))
            },
            onConfirmation = {
                verifyViewModel.onEvent(VerifyEvent.UpdateRulesDialogVisibility(false))
            }
        )
    }

    KeyboardHandler(
        onKeyboardOpen = {
            verifyViewModel.onEvent(VerifyEvent.KeyboardOpen)
        },
        onKeyboardClose = {
            verifyViewModel.onEvent(VerifyEvent.KeyboardClose)
        }
    )

    LaunchedEffect(key1 = verifyState.timeLeft ){
        delay(1000)
        if (verifyState.timeLeft == 0L) {
            verifyViewModel.onEvent(VerifyEvent.UpdateResendCodeState(true))
            verifyViewModel.onEvent(VerifyEvent.UpdateActionLabel(UIText.StringResource(R.string.receive_again_code).asString(context)))
        }else{
            verifyViewModel.onEvent(VerifyEvent.UpdateTimeLeft)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = { paddingValues ->
            //screen content
            val modifier = Modifier.padding(paddingValues)
            MainBox(modifier = modifier){
                Column() {
                    Spacer(modifier = Modifier.height(32.dp))
                    AnimatedVisibility(
                        visible = verifyState.keyboardState == AppKeyboard.Closed,
                        enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Branding(modifier = Modifier.fillMaxWidth())

                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    VerifyMiddleContent(
                        mobileNumber = verifyState.mobileNumber,
                        verifyCode = verifyState.verifyCode,
                        isEnabled = !verifyState.isResendCodeState,
                        onVerifyCodeUpdate = {newValue ->
                            verifyViewModel.onEvent(VerifyEvent.UpdateVerifyCodeState(newValue,UIText.StringResource(R.string.login).asString(context)))
                        },
                        onSend = {
                            verifyViewModel.onEvent(VerifyEvent.VerifyClicked(onNavigateToProfile = onNavigateToProfile))
                        },
                        onMakeVerifyCodeEmpty = {
                            verifyViewModel.onEvent(VerifyEvent.MakeVerifyCodeEmpty)
                        }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    RulesRow(
                        isChecked = verifyState.isRulesAccepted,
                        onRulesClick = {
                            verifyViewModel.onEvent(VerifyEvent.AcceptRules)
                            verifyViewModel.onEvent(VerifyEvent.UpdateRulesDialogVisibility(true))
                        })
                }
                PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    firstText = verifyState.timer ,
                    secondText = verifyState.actionLabel ,
                    status = verifyState.isTimerVisible,
                    onClick = {
                        verifyViewModel.onEvent(VerifyEvent.VerifyClicked(onNavigateToProfile = onNavigateToProfile))
                    })

            }
        }
    )
}

@Composable
fun VerifyMiddleContent(
    mobileNumber: String,
    verifyCode: String,
    isEnabled: Boolean,
    onVerifyCodeUpdate: (String) -> Unit,
    onMakeVerifyCodeEmpty: () -> Unit,
    onSend: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
        {
            TextTitleMedium(text = stringResource(id = R.string.enter_the_sent_code, mobileNumber))
        }
        Spacer(modifier = Modifier.height(48.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                painter = painterResource(id = R.mipmap.ic_barcode_brown),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            val configuration = LocalConfiguration.current
            val textFieldWidth = 0.35 * configuration.screenWidthDp.dp
            TextField(
                modifier = Modifier.width(textFieldWidth),
                value = verifyCode,
                enabled = isEnabled,
                onValueChange = onVerifyCodeUpdate,
                textStyle = MaterialTheme.typography.titleMedium,
                placeholder = {
                    TextBodyMedium(text = stringResource(id = R.string.verify_hint))
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {onSend()}),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = verifyCode.isNotEmpty(),
                        enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { onMakeVerifyCodeEmpty() }
                        ) {
                            Icon(
                                painterResource(id = R.mipmap.ic_close_brown),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun RulesRow(
    isChecked: Boolean,
    onRulesClick : () -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                onRulesClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
        {
            TextTitleSmall(text = stringResource(id = R.string.i_accept_this_app))
            TextTitleSmallPrimary(text = stringResource(id = R.string.terms_of_services), modifier = Modifier.padding(horizontal = 4.dp))
            TextTitleSmall(text = stringResource(id = R.string.all))
            Image(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(32.dp),
                painter = if (isChecked) {
                    painterResource(id = R.mipmap.ic_checked_brown)
                }else{
                    painterResource(id = R.mipmap.ic_un_checked_brown)
                },
                contentDescription = null,
                alignment = Alignment.Center

            )
        }
    }
}
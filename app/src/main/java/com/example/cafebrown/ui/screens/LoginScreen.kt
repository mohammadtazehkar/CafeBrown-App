package com.example.cafebrown.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.LoginEvent
import com.example.cafebrown.presentation.viewmodels.LoginViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.KeyboardHandler
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextBodyMedium
import com.example.cafebrown.ui.components.TextTitleLarge
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.utils.AppKeyboard
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToVerify: (String) -> Unit
) {

    val context = LocalContext.current
    val loginState = loginViewModel.loginState.value
    val snackBarHostState = remember { SnackbarHostState() }



    LaunchedEffect(key1 = true) {
        loginViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> {}
            }
        }
    }

    KeyboardHandler(
        onKeyboardOpen = {
            loginViewModel.onEvent(LoginEvent.KeyboardOpen)
        },
        onKeyboardClose = {
            loginViewModel.onEvent(LoginEvent.KeyboardClose)
        }
    )

    if (loginState.isLoading) {
        ProgressBarDialog()
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
                        visible = loginState.keyboardState == AppKeyboard.Closed,
                        enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Branding(modifier = Modifier.fillMaxWidth())

                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    LoginMiddleContent(
                        mobileNumber = loginState.mobileNumber,
                        onMobileUpdate = {newValue ->
                            loginViewModel.onEvent(LoginEvent.UpdateMobileState(newValue))
                        },
                        onSend = {
                            loginViewModel.onEvent(LoginEvent.LoginClicked(onNavigateToVerify = onNavigateToVerify))
                        },
                        onMakeMobileEmpty = {
                            loginViewModel.onEvent(LoginEvent.MakeMobileEmpty)
                        }
                    )
                }
                PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(id = R.string.send),
                    onClick = {
                        loginViewModel.onEvent(LoginEvent.LoginClicked(onNavigateToVerify = onNavigateToVerify))
                    })

            }
        }
    )
}

@Composable
fun Branding(
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val logoSize = 0.5 * configuration.screenWidthDp.dp

    Column(modifier = modifier) {
        Image(
            modifier = modifier
                .size(logoSize)
                .padding(bottom = 24.dp),
            painter = painterResource(R.drawable.app_logo),
            contentDescription = null,
            alignment = Alignment.Center

        )

        TextTitleLarge(
            modifier = modifier,
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LoginMiddleContent(
    mobileNumber: String,
    onMobileUpdate: (String) -> Unit,
    onMakeMobileEmpty: () -> Unit,
    onSend: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        TextTitleMedium(text = stringResource(id = R.string.please_enter_your_mobile_number))
        Spacer(modifier = Modifier.height(48.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(
                painter = painterResource(id = R.mipmap.ic_mobile_brown),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            TextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(64.dp),
                value = stringResource(id = R.string.mobile_pre_number),
                onValueChange = {  },
                enabled = false,
                textStyle = MaterialTheme.typography.titleMedium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                ),
                maxLines = 1
            )
            TextField(
                modifier = Modifier.weight(1f),
                value = mobileNumber,
                onValueChange = onMobileUpdate,
                textStyle = MaterialTheme.typography.titleMedium,
                placeholder = {
                    TextBodyMedium(text = stringResource(id = R.string.mobile_hint))
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
                        visible = mobileNumber.isNotEmpty(),
                        enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { onMakeMobileEmpty() }
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
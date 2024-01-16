package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.LoginEvent
import com.example.cafebrown.presentation.events.ProfileEvent
import com.example.cafebrown.presentation.viewmodels.ProfileViewModel
import com.example.cafebrown.ui.components.AppDatePicker
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.TextBodyMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.utils.Destinations.VERIFY_SCREEN
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    onSignUpCompleted: () -> Unit
) {
    val context = LocalContext.current
    val profileState = profileViewModel.profileState
    val errorSnackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        profileViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    errorSnackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            AppTopAppBar(
                title =
                if (profileState.value.from == VERIFY_SCREEN) {
                    stringResource(id = R.string.sign_up)
                } else {
                    stringResource(id = R.string.profile)
                }
            )
        },
        snackbarHost = {
            SnackbarHost(errorSnackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = { paddingValues ->
            MainBox(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .align(Alignment.TopCenter),
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        ProfileTextField(
                            state = profileState.value.firstName,
                            label = stringResource(id = R.string.first_name),
                            leadingIconId = R.mipmap.ic_user_brown,
                            onValueChange = { newVal ->
                                profileViewModel.onEvent(ProfileEvent.UpdateFirstNameState(newVal))
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ProfileTextField(
                            state = profileState.value.lastName,
                            label = stringResource(id = R.string.last_name),
                            leadingIconId = R.mipmap.ic_user_brown,
                            onValueChange = { newVal ->
                                profileViewModel.onEvent(ProfileEvent.UpdateLastNameState(newVal))
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ProfileTextField(
                            state = profileState.value.mobileNumber,
                            label = stringResource(id = R.string.mobile_number),
                            leadingIconId = R.mipmap.ic_mobile_brown,
                            onValueChange = {},
                            isEnable = false
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileGenderRow(
                        gender = profileState.value.gender,
                        onGenderClick = { status ->
                            profileViewModel.onEvent(ProfileEvent.UpdateGenderState(status))
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileBirthDateRow(
                        yearList = profileState.value.yearsList,
                        monthList = profileState.value.monthList,
                        dayList = profileState.value.daysList,
                        onSelectedYear = { selected ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedYear(selected))
                        },
                        onSelectedMonth = { selected ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedMonth(selected))
                        },
                        onSelectedDay = { selected ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedDay(selected))
                        }
                    )

                }

                PrimaryButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = stringResource(
                        id =
                        if (profileState.value.from == VERIFY_SCREEN) {
                            R.string.sign_up
                        } else {
                            R.string.submit
                        }
                    ),
                    onClick = {
                        if (profileState.value.from == VERIFY_SCREEN) {
                            profileViewModel.onEvent(ProfileEvent.SignUpClicked(onSignUpCompleted))
                        } else {
                            profileViewModel.onEvent(ProfileEvent.UpdateClicked)
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun ProfileTextField(
    isEnable: Boolean = true,
    state: String,
    label: String,
    leadingIconId: Int,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state,
        onValueChange = onValueChange,
        enabled = isEnable,
        textStyle = MaterialTheme.typography.titleMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Image(
                painter = painterResource(id = leadingIconId),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        },
        placeholder = {
            TextBodyMedium(text = label)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun ProfileGenderRow(
    gender: Boolean,
    onGenderClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onGenderClick(false)
            }) {
            TextTitleSmall(text = stringResource(id = R.string.female))
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                painter = painterResource(
                    id =
                    if (gender) {
                        R.mipmap.ic_un_checked_brown
                    } else {
                        R.mipmap.ic_checked_brown
                    }
                ), contentDescription = null
            )
        }
        Row(modifier = Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                onGenderClick(true)
            }) {
            TextTitleSmall(text = stringResource(id = R.string.male))
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                painter = painterResource(
                    id =
                    if (gender) {
                        R.mipmap.ic_checked_brown
                    } else {
                        R.mipmap.ic_un_checked_brown
                    }
                ), contentDescription = null
            )
        }
        TextTitleSmall(
            modifier = Modifier.padding(start = 24.dp),
            text = stringResource(id = R.string.gender)
        )
        Image(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(32.dp),
            painter = painterResource(id = R.mipmap.ic_gender_brown),
            contentDescription = null
        )
    }
}

@Composable
fun ProfileBirthDateRow(
    yearList: List<String>,
    monthList: List<String>,
    dayList: List<String>,
    onSelectedYear: (String) -> Unit,
    onSelectedMonth: (String) -> Unit,
    onSelectedDay: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppDatePicker(
            yearList = yearList,
            monthList = monthList,
            dayList = dayList,
            onSelectedYear = onSelectedYear,
            onSelectedMonth = onSelectedMonth,
            onSelectedDay = onSelectedDay
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextTitleSmall(text = stringResource(id = R.string.birth_date))
        Image(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(32.dp),
            painter = painterResource(id = R.mipmap.ic_calendar2_brown),
            contentDescription = null
        )
    }
}

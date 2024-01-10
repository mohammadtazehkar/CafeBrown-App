package com.example.cafebrown.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.ProfileEvent
import com.example.cafebrown.presentation.viewmodels.ProfileViewModel
import com.example.cafebrown.ui.components.AppDatePicker
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
){
    val profileState = profileViewModel.profileState

    Scaffold (
        topBar = {
                 AppTopAppBar(title = stringResource(id = R.string.profile))
        },
        snackbarHost = {},
        content = {paddingValues ->
            MainColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row (modifier = Modifier.fillMaxWidth()){
                    AppDatePicker(
                        yearList = profileState.value.yearsList,
                        monthList = profileState.value.monthList,
                        dayList = profileState.value.daysList,
                        onSelectedYear = {selectedValue ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedYear(selectedValue))
                        },
                        onSelectedMonth = {selectedValue ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedMonth(selectedValue))
                        },
                        onSelectedDay = {selectedValue ->
                            profileViewModel.onEvent(ProfileEvent.UpdateSelectedDay(selectedValue))
                        }
                    )
                }
            }
        }
    )
}


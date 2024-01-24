package com.example.cafebrown.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.DeskListEvent
import com.example.cafebrown.presentation.events.ReserveEvent
import com.example.cafebrown.presentation.viewmodels.ReserveViewModel
import com.example.cafebrown.ui.components.AppDatePicker
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.PrimaryButtonExtraSmallCorner
import com.example.cafebrown.ui.components.PrimarySmallButton
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.SecondaryButton
import com.example.cafebrown.ui.components.TextLabelMedium
import com.example.cafebrown.ui.components.TextLabelSmall
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumError
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallError
import com.example.cafebrown.ui.components.TextTitleSmallPrimary
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.UIText

@Composable
fun ReserveScreen(
    reserveViewModel: ReserveViewModel = hiltViewModel(),
    onNavigateToMenu: () -> Unit,
    onNavUp: () -> Unit,

    ) {
    val context = LocalContext.current
    var reserveState = reserveViewModel.reserveState.value
    val errorSnackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        reserveViewModel.uiEventFlow.collect() { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    val result = errorSnackBarHostState
                        .showSnackbar(
                            message = event.message.asString(context),
                            actionLabel = UIText.StringResource(R.string.try_again)
                                .asString(context),
                            duration = SnackbarDuration.Indefinite
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            reserveViewModel.onEvent(ReserveEvent.GetReserveTimes(reserveState.tableId))
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        Log.i("meyti", "LaunchedEffect Call")
        reserveViewModel.onEvent(ReserveEvent.GetReserveTimes(reserveState.tableId))
    }

    if (reserveState.isLoading) {
        ProgressBarDialog {}
    }

    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.reserve), true, onNavUp)
        },
        content = { paddingValues ->
            MainBox(
                modifier = Modifier.padding(paddingValues)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ReserveTopScreen(
                        reserveState.capacity.toString(),
                        reserveState.status,
                        reserveState.number.toString(),
                        onNavigateToMenu
                    )
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    ReserveDateScreen(
                        reserveState.yearsList,
                        reserveState.monthList,
                        reserveState.daysList,
                        { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedYear(it)) },
                        { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedMonth(it)) },
                        { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedDay(it)) })
                    if (reserveState.response.data != null) {
                        ReserveTimeScreen(
                            reserveState.hoursList,
                            reserveState.minuteList,
                            reserveState.periodList.map { it.title },
                            { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedHour(it)) },
                            { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedMinute(it)) },
                            { reserveViewModel.onEvent(ReserveEvent.UpdateSelectedPeriod(it)) })
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    if (reserveState.isReserveTimeChecked) {
                        TextTitleSmallPrimary(text = stringResource(id = R.string.selected_date_is_ready_for_reserve))
                    } else {
                        TextTitleSmallError(text = stringResource(id = R.string.selected_date_is_not_valid                                                                                                                                                                         ))
                    }
                }

                if (reserveState.isEdit){
                    PrimaryButton(text = stringResource(id = R.string.edit), onClick = {}, modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(16.dp))
                }else{
                    if (reserveState.isReserveTimeChecked){
                        PrimaryButton(text = stringResource(id = R.string.edit), onClick = {}, modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(16.dp))
                    }else{
                        SecondaryButton(text = stringResource(id = R.string.check_table_status), onClick = {}, modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(16.dp))
                    }
                }
            }
        }
    )
}

@Composable
fun ReserveTopScreen(
    capacity: String,
    isFull: Boolean,
    deskNum: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PrimarySmallButton(
            modifier = Modifier,
            text = stringResource(id = R.string.order), onClick = onClick
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Row {
                    TextTitleSmall(
                        modifier = Modifier.padding(end = 4.dp),
                        text = stringResource(id = R.string.capacity_of_desk_is)
                    )
                    TextTitleSmallPrimary(text = "${capacity} ${stringResource(id = R.string.person)}")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    TextTitleSmall(
                        modifier = Modifier.padding(end = 4.dp),
                        text = stringResource(id = R.string.status_at_the_moment_is)
                    )
                    if (isFull) {
                        TextTitleSmallError(text = stringResource(id = R.string.full))
                    } else {
                        TextTitleSmallPrimary(text = stringResource(id = R.string.empty))
                    }
                }
            }
        }
        Box(modifier = Modifier.padding()) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(64.dp),
                painter = painterResource(id = R.mipmap.ic_desk), contentDescription = ""
            )
            TextLabelMedium(
                modifier = Modifier.align(Alignment.Center),
                text = deskNum
            )
        }
    }
}

@Composable
fun ReserveDateScreen(
    yearList: List<String>,
    monthList: List<String>,
    dayList: List<String>,
    onSelectedYear: (String) -> Unit,
    onSelectedMonth: (String) -> Unit,
    onSelectedDay: (String) -> Unit,
) {

    TextTitleMedium(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.End,
        text = stringResource(id = R.string.please_select_your_reserve_date)
    )

    AppDatePicker(
        yearList = yearList,
        monthList = monthList,
        dayList = dayList,
        onSelectedYear = onSelectedYear,
        onSelectedMonth = onSelectedMonth,
        onSelectedDay = onSelectedDay
    )
}

@Composable
fun ReserveTimeScreen(
    hoursList: List<String>,
    minuteList: List<String>,
    periodList: List<String>,
    onSelectedHours: (String) -> Unit,
    onSelectedMinute: (String) -> Unit,
    onSelectedPeriod: (String) -> Unit,
) {

    TextTitleMedium(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.End,
        text = stringResource(id = R.string.please_select_your_reserve_time_and_duration)
    )

    AppDatePicker(
        yearList = hoursList,
        monthList = minuteList,
        dayList = periodList,
        onSelectedYear = onSelectedHours,
        onSelectedMonth = onSelectedMinute,
        onSelectedDay = onSelectedPeriod,
        width = 128.dp
    )
}

//@Preview(showBackground = false, showSystemUi = true)
//@Composable
//fun SimpleView() {
//    AppTheme {
//        Surface {
//            ReserveScreen()
//        }
//    }
//}
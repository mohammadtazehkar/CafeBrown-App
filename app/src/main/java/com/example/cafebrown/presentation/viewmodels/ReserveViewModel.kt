package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetReserveBaseInfoUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ReserveEvent
import com.example.cafebrown.presentation.states.ReserveState
import com.example.cafebrown.utils.ArgumentKeys
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getReserveBaseInfoUseCase: GetReserveBaseInfoUseCase
) : ViewModel() {

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()


    private val _reverseState = mutableStateOf(
        ReserveState(
            tableId = savedStateHandle.get<Int>(ArgumentKeys.DESK_ID)!!,
            capacity = savedStateHandle.get<Int>(ArgumentKeys.CAPACITY)!!,
            number = savedStateHandle.get<Int>(ArgumentKeys.TABLE_NUMBER)!!,
            status = savedStateHandle.get<Boolean>(ArgumentKeys.STATUS)!!,
            response = Resource.Error(""),
            yearsList = (1320..1402).map { it.toString() },
            monthList = (1..12).map { it.toString() },
            daysList = (1..31).map { it.toString() },
            hoursList = (0..24).map { it.toString() },
            minuteList = listOf("00", "15", "30", "45")
        )
    )

    var reserveState: State<ReserveState> = _reverseState

    fun onEvent(event: ReserveEvent) {
        when (event) {
            is ReserveEvent.UpdateSelectedPeriod -> {
                _reverseState.value = reserveState.value.copy(
                    selectedPeriod = event.newSelectedPeriod
                )
                var cost = _reverseState.value.response.data?.data?.reserveTimeData?.indexOfFirst { it.title == _reverseState.value.selectedPeriod }

            }
            is ReserveEvent.UpdateSelectedHour -> _reverseState.value = reserveState.value.copy(
                selectedPeriod = event.newSelectedHour
            )

            is ReserveEvent.UpdateSelectedMinute -> _reverseState.value = reserveState.value.copy(
                selectedPeriod = event.newSelectedMinute
            )

            is ReserveEvent.UpdateSelectedDay -> _reverseState.value =
                reserveState.value.copy(selectedDay = event.newSelectedDay)

            is ReserveEvent.UpdateSelectedMonth -> {
                _reverseState.value = reserveState.value.copy(selectedDay = event.newSelectedMonth)
                changeMonthsDays(event.newSelectedMonth.toInt())
            }

            is ReserveEvent.UpdateSelectedYear -> {
                _reverseState.value = reserveState.value.copy(selectedDay = event.newSelectedYear)
                checkLeapYear(event.newSelectedYear.toInt())
            }

            is ReserveEvent.GetReserveTimes -> getReserveTimes()
        }
    }

    private fun checkLeapYear(newYear: Int) {
        val isLeap =
            (newYear % 33 == 1 || newYear % 33 == 5 || newYear % 33 == 9 || newYear % 33 == 13 ||
                    newYear % 33 == 17 || newYear % 33 == 22 || newYear % 33 == 26 || newYear % 33 == 30)
        _reverseState.value = reserveState.value.copy(
            isLeap = isLeap
        )
        changeMonthsDays(reserveState.value.selectedMonth.toInt())
    }

    private fun changeMonthsDays(selectedMonth: Int) {
        if (selectedMonth < 7) {
            val newDayList: List<String> = (1..31).map { it.toString() }
            _reverseState.value = reserveState.value.copy(
                daysList = newDayList
            )
        } else {
            if ((selectedMonth == 12 && reserveState.value.isLeap) || (selectedMonth != 12)) {
                val newDayList: List<String> = (1..30).map { it.toString() }
                _reverseState.value = reserveState.value.copy(
                    daysList = newDayList
                )

            } else {
                val newDayList: List<String> = (1..29).map { it.toString() }
                _reverseState.value = reserveState.value.copy(
                    daysList = newDayList
                )

            }
        }
    }

    private fun getReserveTimes() {
        _reverseState.value = reserveState.value.copy(
            response = Resource.Loading(),
            isLoading = false
        )

        viewModelScope.launch {
            _reverseState.value = reserveState.value.copy(
                response = getReserveBaseInfoUseCase.execute(_reverseState.value.tableId)
            )
            when (_reverseState.value.response.data?.status) {

                JSonStatusCode.BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(_reverseState.value.response.data?.message!!)
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {

                    _reverseState.value = reserveState.value.copy(
                        response = _reverseState.value.response,
                        periodList = _reverseState.value.response.data?.data!!.reserveTimeData
                    )
                }

                JSonStatusCode.INTERNET_CONNECTION -> {
                    Log.i("meyti", "emit")
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }

                JSonStatusCode.SERVER_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }

            }
            _reverseState.value = reserveState.value.copy(
                isLoading = false
            )
        }
    }

}
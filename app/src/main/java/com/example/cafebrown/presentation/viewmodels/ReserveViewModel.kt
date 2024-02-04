package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.data.models.reserve.APIReserveCheckRequest
import com.example.cafebrown.domain.usecase.GetReserveBaseInfoUseCase
import com.example.cafebrown.domain.usecase.PostReserveCheckUseCase
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
    private val getReserveBaseInfoUseCase: GetReserveBaseInfoUseCase,
    private val postReserveCheckUseCase: PostReserveCheckUseCase
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
            minuteList = listOf("00", "15", "30", "45"),
            reserveCheckRequest = Resource.Error(""),
            actionLabel = UIText.StringResource(R.string.check_table_status)
        )
    )

    var reserveState: State<ReserveState> = _reverseState

    fun onEvent(event: ReserveEvent) {
        when (event) {
            is ReserveEvent.UpdateSelectedPeriod -> {
                _reverseState.value = reserveState.value.copy(
                    selectedPeriod = event.newSelectedPeriod
                )
                var cost =
                    _reverseState.value.response.data?.data?.reserveTimeData?.indexOfFirst { it.title == _reverseState.value.selectedPeriod }

            }

            is ReserveEvent.UpdateSelectedHour -> _reverseState.value = reserveState.value.copy(
                selectedHours = event.newSelectedHour,
                isSelectedTimeChange = true
            )

            is ReserveEvent.UpdateSelectedMinute -> _reverseState.value = reserveState.value.copy(
                selectedMinute = event.newSelectedMinute,
                isSelectedTimeChange = true
            )

            is ReserveEvent.UpdateSelectedDay -> _reverseState.value =
                reserveState.value.copy(
                    selectedDay = event.newSelectedDay,
                    isSelectedTimeChange = true
                )

            is ReserveEvent.UpdateSelectedMonth -> {
                _reverseState.value = reserveState.value.copy(
                    selectedMonth = event.newSelectedMonth,
                    isSelectedTimeChange = true
                )
                changeMonthsDays(event.newSelectedMonth.toInt())
            }

            is ReserveEvent.UpdateSelectedYear -> {
                _reverseState.value = reserveState.value.copy(
                    selectedYear = event.newSelectedYear,
                    isSelectedTimeChange = true
                )
                checkLeapYear(event.newSelectedYear.toInt())
            }

            is ReserveEvent.GetReserveTimes -> getReserveTimes()

            is ReserveEvent.PostReserveCheck -> postReserveCheck()

            is ReserveEvent.ActionClick -> postReserveCheck()
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
            isLoading = true
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
                    _reverseState.value = reserveState.value.copy(
                        response = Resource.Error(_reverseState.value.response.data?.message.toString())
                    )
                    Log.i("meyti", "emit")
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }

                JSonStatusCode.SERVER_CONNECTION -> {
                    _reverseState.value = reserveState.value.copy(
                        response = Resource.Error(_reverseState.value.response.data?.message.toString())
                    )
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

    private fun postReserveCheck() {
        _reverseState.value = reserveState.value.copy(
            reserveCheckRequest = Resource.Loading(),
            isLoading = true
        )

        viewModelScope.launch {
            val apiReserveCheckRequest = APIReserveCheckRequest(
                tableId = _reverseState.value.tableId,
                reserveTimeId = _reverseState.value.response.data?.data?.reserveTimeData!!.first { it.title == _reverseState.value.selectedPeriod }.id,
                date = _reverseState.value.selectedYear + "/" + _reverseState.value.selectedMonth + "/" + _reverseState.value.selectedDay,
                time = _reverseState.value.selectedHours + ":" + _reverseState.value.selectedMinute
            )
            _reverseState.value = reserveState.value.copy(
                reserveCheckRequest = postReserveCheckUseCase.execute(
                    apiReserveCheckRequest
                )
            )


            Log.i("Meyti", apiReserveCheckRequest.toString())


            when (_reverseState.value.reserveCheckRequest.data?.status) {
                JSonStatusCode.BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(_reverseState.value.reserveCheckRequest.data?.message!!)
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {

                    _reverseState.value = reserveState.value.copy(
                        reserveCheckRequest = _reverseState.value.reserveCheckRequest
                    )
                    val result =
                        _reverseState.value.reserveCheckRequest.data?.status.toString() + " " + _reverseState.value.reserveCheckRequest.data?.message;
                    Log.i("meyti", result)
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
                    Log.i("meyti", _reverseState.value.reserveCheckRequest.data?.status.toString())
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }

            }

            Log.i("meyti", "Before isLoading = false")
            _reverseState.value = reserveState.value.copy(
                Log.i("meyti", "isLoading = false"),
                isLoading = false
            )

        }
    }
}
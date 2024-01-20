package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cafebrown.presentation.events.ReserveEvent
import com.example.cafebrown.presentation.states.ReserveState
import com.example.cafebrown.utils.ArgumentKeys

class ReserveViewModel : ViewModel() {

    private val _reverseState = mutableStateOf(
        ReserveState(
            yearsList = (1320..1402).map { it.toString() },
            monthList = (1..12).map { it.toString() },
            daysList = (1..31).map { it.toString() },
            hoursList = (0 .. 24).map { it.toString() },
            minuteList = listOf("00","15","30","45"),
            periodList = listOf("تا 1 ساعت","تا 2 ساعت","تا 3 ساعت","تا 4 ساعت")
        )
    )

    var reserveState : State<ReserveState> = _reverseState

    fun onEvent (event : ReserveEvent){
        when(event){
            is ReserveEvent.UpdateSelectedPeriod -> _reverseState.value = reserveState.value.copy(
                selectedPeriod = event.newSelectedPeriod
            )

            is ReserveEvent.UpdateSelectedHour -> _reverseState.value = reserveState.value.copy(
                selectedPeriod = event.newSelectedHour
            )
            is ReserveEvent.UpdateSelectedMinute -> _reverseState.value = reserveState.value.copy(
                selectedPeriod = event.newSelectedMinute
            )

            is ReserveEvent.UpdateSelectedDay -> _reverseState.value = reserveState.value.copy(selectedDay = event.newSelectedDay)
            is ReserveEvent.UpdateSelectedMonth -> {
                _reverseState.value = reserveState.value.copy(selectedDay = event.newSelectedMonth)
                changeMonthsDays(event.newSelectedMonth.toInt())
            }
            is ReserveEvent.UpdateSelectedYear -> {
                _reverseState.value = reserveState.value.copy(selectedDay = event.newSelectedYear)
                checkLeapYear(event.newSelectedYear.toInt())
            }
        }
    }

    private fun checkLeapYear(newYear : Int){
        val isLeap = ( newYear % 33 == 1 || newYear % 33 == 5 || newYear % 33 == 9 || newYear % 33 == 13 ||
                newYear % 33 == 17 || newYear % 33 == 22 ||newYear % 33 == 26 ||newYear % 33 == 30)
        _reverseState.value = reserveState.value.copy(
            isLeap = isLeap
        )
        changeMonthsDays(reserveState.value.selectedMonth.toInt())
    }

    private fun changeMonthsDays(selectedMonth: Int){
        if (selectedMonth < 7){
            val newDayList : List<String> =  (1..31).map { it.toString() }
            _reverseState.value = reserveState.value.copy(
                daysList = newDayList
            )
        }else{
            if ((selectedMonth == 12 && reserveState.value.isLeap) || (selectedMonth != 12)){
                val newDayList : List<String> =  (1..30).map { it.toString() }
                _reverseState.value = reserveState.value.copy(
                    daysList = newDayList
                )

            }else{
                val newDayList : List<String> =  (1..29).map { it.toString() }
                _reverseState.value = reserveState.value.copy(
                    daysList = newDayList
                )

            }
        }
    }

}
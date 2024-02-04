package com.example.cafebrown.presentation.events

sealed class ReserveEvent {
    data class UpdateSelectedPeriod(var newSelectedPeriod : String) : ReserveEvent()
    data class UpdateSelectedMinute(var newSelectedMinute : String) : ReserveEvent()
    data class UpdateSelectedHour(var newSelectedHour : String) : ReserveEvent()
    data class UpdateSelectedYear(var newSelectedYear : String) : ReserveEvent()
    data class UpdateSelectedMonth(var newSelectedMonth : String) : ReserveEvent()
    data class UpdateSelectedDay(var newSelectedDay : String) : ReserveEvent()
    data class GetReserveTimes(var tableId: Int): ReserveEvent()
    data object PostReserveCheck: ReserveEvent()
    data object ActionClick: ReserveEvent()
}
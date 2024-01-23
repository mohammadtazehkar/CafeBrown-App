package com.example.cafebrown.presentation.events

sealed class ProfileEvent{
    data class UpdateFirstNameState(val newValue: String): ProfileEvent()
    data class UpdateLastNameState(val newValue: String): ProfileEvent()
    data class UpdateGenderState(val status: Boolean): ProfileEvent()
    data class ActionClicked(val onSignUpCompleted: () -> Unit, val onUpdateCompleted: () -> Unit) : ProfileEvent()
    data class UpdateSelectedYear(val newValue: String) : ProfileEvent()
    data class UpdateSelectedMonth(val newValue: String) : ProfileEvent()
    data class UpdateSelectedDay(val newValue: String) : ProfileEvent()

}

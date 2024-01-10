package com.example.cafebrown.presentation.events

sealed class ProfileEvent{
    data class UpdateFirstNameState(val newValue: String): ProfileEvent()
    data class UpdateLastNameState(val newValue: String): ProfileEvent()
    data class UpdateGenderState(val status: Boolean): ProfileEvent()
    data object UpdateClicked : ProfileEvent()
    data class SignUpClicked(val onSignUpCompleted: () -> Unit) : ProfileEvent()
    data class UpdateLoading(var status: Boolean): ProfileEvent()
    data object GetProfileData : ProfileEvent()
    data class PrepareData(val fromUpdate: Boolean) : ProfileEvent()

    data class UpdateSelectedYear(val newValue: String) : ProfileEvent()
    data class UpdateSelectedMonth(val newValue: String) : ProfileEvent()
    data class UpdateSelectedDay(val newValue: String) : ProfileEvent()

}

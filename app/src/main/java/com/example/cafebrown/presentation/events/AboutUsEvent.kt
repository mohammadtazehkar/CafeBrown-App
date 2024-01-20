package com.example.cafebrown.presentation.events

sealed class AboutUsEvent {

    data class ChangeComplaintDialogVisibility(val status: Boolean) : AboutUsEvent()
    data class ChangeComplaintData(val text: String) : AboutUsEvent()
    data class ChangeRulesDialogVisibility(val status: Boolean) : AboutUsEvent()

}
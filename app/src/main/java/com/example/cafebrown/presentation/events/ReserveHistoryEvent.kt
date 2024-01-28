package com.example.cafebrown.presentation.events

sealed class ReserveHistoryEvent{
    data object GetListFromServer: ReserveHistoryEvent()

}

package com.example.cafebrown.presentation.events

sealed class DeskListEvent {
    data object GetDeskList : DeskListEvent()
}
package com.example.cafebrown.presentation.events

sealed class HomeEvent {
    data object GetImageList : HomeEvent()
}
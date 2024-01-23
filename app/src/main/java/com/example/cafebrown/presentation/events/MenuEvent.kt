package com.example.cafebrown.presentation.events

sealed class MenuEvent{
    data object GetMenuList : MenuEvent()
}

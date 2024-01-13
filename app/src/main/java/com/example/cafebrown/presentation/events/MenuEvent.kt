package com.example.cafebrown.presentation.events

sealed class MenuEvent{
    data class UpdateLoading(val status: Boolean) : MenuEvent()
//    data object GetHomeData : MenuCategoryEvent()
//    data object PrepareData : MenuCategoryEvent()

}

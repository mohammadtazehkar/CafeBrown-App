package com.example.cafebrown.presentation.states

import com.example.cafebrown.ui.screens.MenuItemData


data class MenuState(
    var isLoading : Boolean = true,
    var menuListState : List<MenuItemData> = mutableListOf(),
    var from : String
//    var response: Resource<APIHomeDataResponse>
)

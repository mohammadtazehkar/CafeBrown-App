package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.models.menu.GetMenuResponse
import com.example.cafebrown.utils.Resource


data class MenuState(
    var isLoading : Boolean = false,
    var menuListState : List<GetMenuResponse> = emptyList(),
    var from : String,
    var response: Resource<APIGetMenuResponse>
)

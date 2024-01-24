package com.example.cafebrown.presentation.states

import android.app.AlertDialog
import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.models.desk.GetDeskResponse
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.models.home.HomeImage
import com.example.cafebrown.ui.screens.DeckItemData
import com.example.cafebrown.utils.Resource

data class DeskState (
    var isLoading: Boolean = false,
    var response: Resource<APIGetDeskResponse>,
    var deskList: List<GetDeskResponse> = emptyList()
)
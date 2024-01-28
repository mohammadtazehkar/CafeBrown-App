package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.reserveHistory.APIGetUserReserveResponse
import com.example.cafebrown.data.models.reserveHistory.GetUserReserveResponse
import com.example.cafebrown.utils.Resource


data class ReserveHistoryState (
    val isLoading: Boolean = false,
    var reserveList : List<GetUserReserveResponse> = mutableListOf(),
    var response: Resource<APIGetUserReserveResponse> = Resource.Error("")
)
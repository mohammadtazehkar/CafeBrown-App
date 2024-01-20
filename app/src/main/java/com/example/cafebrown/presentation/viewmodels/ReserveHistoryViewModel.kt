package com.example.cafebrown.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cafebrown.presentation.states.ReserveHistoryState
import com.example.cafebrown.ui.screens.ReserveItemData
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class ReserveHistoryViewModel : ViewModel() {
    private val _reserveHistoryState = mutableStateOf(
        ReserveHistoryState(
            reserveList = mutableListOf(
                ReserveItemData(1,"1397/1/1 - 12:54","1",false),
                ReserveItemData(2,"1397/1/1 - 12:54","2",false),
                ReserveItemData(3,"1397/1/1 - 12:54","1",true),
                ReserveItemData(4,"1397/1/1 - 12:54","5",false),
                ReserveItemData(5,"1397/1/1 - 12:54","3",true),
            )
        )
    )

    val reserveHistoryState : State<ReserveHistoryState> = _reserveHistoryState
}
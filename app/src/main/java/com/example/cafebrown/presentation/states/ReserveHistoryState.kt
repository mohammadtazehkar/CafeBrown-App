package com.example.cafebrown.presentation.states

import com.example.cafebrown.ui.screens.ReserveItemData

data class ReserveHistoryState (
    var reserveList : List<ReserveItemData> = mutableListOf()
)
package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cafebrown.presentation.states.DeskState
import com.example.cafebrown.presentation.states.HomeState
import com.example.cafebrown.ui.screens.DeckItemData

class DeskListViewModel() : ViewModel() {

    private val _deskListState = mutableStateOf(
        DeskState(
            deskList = mutableListOf(
                DeckItemData(1, "2", "1", false),
                DeckItemData(1, "4", "2", true),
                DeckItemData(1, "6", "3", false),
                DeckItemData(1, "8", "4", false),
                DeckItemData(1, "10", "5", true)
            ), isDialogVisible = false
        )
    )

    val deskState: State<DeskState> = _deskListState
}
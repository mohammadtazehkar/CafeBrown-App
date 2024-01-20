package com.example.cafebrown.presentation.states

import android.app.AlertDialog
import com.example.cafebrown.ui.screens.DeckItemData

data class DeskState (
    var deskList : List<DeckItemData> = mutableListOf(),
    var isDialogVisible : Boolean
)
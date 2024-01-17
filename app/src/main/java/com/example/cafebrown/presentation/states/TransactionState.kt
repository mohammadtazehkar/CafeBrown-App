package com.example.cafebrown.presentation.states

import com.example.cafebrown.ui.screens.TransactionItemData

data class TransactionState(
    var isDialogVisible : Boolean = false,
    var balance : String = "",
    var increaseBalance:String = "",
    var transactionList : MutableList<TransactionItemData>
    )

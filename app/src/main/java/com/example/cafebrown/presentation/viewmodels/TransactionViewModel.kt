package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.TransactionEvent
import com.example.cafebrown.presentation.states.TransactionState
import com.example.cafebrown.ui.screens.TransactionItemData

class TransactionViewModel : ViewModel() {

    var transactionlist = mutableListOf(
        TransactionItemData(
            R.mipmap.ic_credit_card, "mamali", "2000", "خرید سفارش", "موفق", "1402/10/25 - 18:30"
        ), TransactionItemData(
            R.mipmap.ic_wallet, "شکر", "1000", "افزایش اعتبار", "موفق", "1402/10/25 - 18:30"
        ), TransactionItemData(
            R.mipmap.ic_credit_card, "شکر", "1000", "افزایش اعتبار", "موفق", "1402/10/25 - 18:30"
        ), TransactionItemData(
            R.mipmap.ic_wallet, "شکر", "1000", "افزایش اعتبار", "موفق", "1402/10/25 - 18:30"
        ), TransactionItemData(
            R.mipmap.ic_credit_card, "شکر", "1000", "افزایش اعتبار", "موفق", "1402/10/25 - 18:30"
        ), TransactionItemData(
            R.mipmap.ic_wallet, "شکر", "1000", "افزایش اعتبار", "موفق", "1402/10/25 - 18:30"
        )
    )

    private val _transactionState = mutableStateOf(
        TransactionState(
            isDialogVisible = false,
            balance = "2500",
            increaseBalance = "150",
            transactionList = transactionlist
        )
    )

    val transactionState: State<TransactionState> = _transactionState

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.ChangeDialogVisibility -> {
                _transactionState.value = transactionState.value.copy(
                    isDialogVisible = event.status
                )
            }

            is TransactionEvent.ChangeIncreaseBalanceTextField -> {
                _transactionState.value = transactionState.value.copy(
                    increaseBalance = event.increaseBalance
                )
            }
        }
    }
}
package com.example.cafebrown.presentation.events

sealed class TransactionEvent {
    data class ChangeDialogVisibility(val status: Boolean) : TransactionEvent()

    //balance for getting from server should be implemented later

    data class ChangeIncreaseBalanceTextField(val increaseBalance: String) : TransactionEvent()

}
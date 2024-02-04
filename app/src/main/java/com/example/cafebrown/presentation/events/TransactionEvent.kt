package com.example.cafebrown.presentation.events

sealed class TransactionEvent {
    data class ChangeDialogVisibility(val status: Boolean) : TransactionEvent()
    data class ChangeIncreaseBalanceTextField(val increaseBalance: String) : TransactionEvent()
    data object GetTransactionListFromServer : TransactionEvent()
    data object PostIncreaseBalanceToServer : TransactionEvent()

}
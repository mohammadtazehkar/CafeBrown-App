package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.data.models.transaction.APIPostIncreaseBalanceRequest
import com.example.cafebrown.domain.usecase.GetTransactionListUseCase
import com.example.cafebrown.domain.usecase.PostIncreaseBalanceUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.TransactionEvent
import com.example.cafebrown.presentation.states.TransactionState
import com.example.cafebrown.utils.JSonStatusCode.BAD_REQUEST
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SUCCESS
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TransactionViewModel @Inject constructor(
    val getTransactionListUseCase: GetTransactionListUseCase,
    val postIncreaseBalanceUseCase: PostIncreaseBalanceUseCase
) : ViewModel() {


    private val _transactionState = mutableStateOf(TransactionState())
    val transactionState: State<TransactionState> = _transactionState
    private val _screenSharedFlow = MutableSharedFlow<AppUIEvent>()
    val screenSharedFlow: SharedFlow<AppUIEvent> = _screenSharedFlow.asSharedFlow()

    private fun getTransactionList() {
        _transactionState.value =
            transactionState.value.copy(isLoading = true, response = Resource.Loading())
        viewModelScope.launch {
            _transactionState.value =
                transactionState.value.copy(response = getTransactionListUseCase.execute())
            when (_transactionState.value.response.data?.status) {
                BAD_REQUEST -> {
                    _screenSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.DynamicString(
                                _transactionState.value.response.data?.message!!
                            )
                        )
                    )
                }

                SUCCESS -> {
                    _transactionState.value = transactionState.value.copy(
                        balance = _transactionState.value.response.data?.data?.balance,
                        transactionList = _transactionState.value.response.data?.data?.transactionsList!!
                    )
                }

                INTERNET_CONNECTION -> {
                    _screenSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.internet_connection_problem)))
                }

                SERVER_CONNECTION -> {
                    _screenSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.connection_problem)))
                }

                EXPIRED_TOKEN -> {
                    _screenSharedFlow.emit(AppUIEvent.ExpiredToken)
                }
            }
            _transactionState.value = transactionState.value.copy(isLoading = false)
        }
    }

    private fun postIncreaseBalance() {
        _transactionState.value =
            transactionState.value.copy(isLoading = true, responsePost = Resource.Loading())
        viewModelScope.launch {
            _transactionState.value = transactionState.value.copy(
                responsePost = postIncreaseBalanceUseCase.execute(
                    APIPostIncreaseBalanceRequest(
                        status = "true",
                        refId = Random.nextInt(1, 1000).toString(),
                        amount = _transactionState.value.increaseBalance.toLong()
                    )
                )
            )
            when (_transactionState.value.responsePost.data?.status) {
                BAD_REQUEST -> {
                    _screenSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.DynamicString(
                                _transactionState.value.responsePost.data?.message!!
                            )
                        )
                    )
                }

                SUCCESS -> {
                    _screenSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.StringResource(R.string.increase_balance_successfully),
                            isError = false
                        )
                    )
                    _transactionState.value = transactionState.value.copy(increaseBalance = "")
//                    val finalBalance =
//                        _transactionState.value.balance?.plus(_transactionState.value.increaseBalance.toLong())
//                    _transactionState.value =
//                        transactionState.value.copy(balance = finalBalance)
                    getTransactionList()
                }

                INTERNET_CONNECTION -> {
                    _screenSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.internet_connection_problem)))
                }

                SERVER_CONNECTION -> {
                    _screenSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.connection_problem)))
                }

                EXPIRED_TOKEN -> {
                    _screenSharedFlow.emit(AppUIEvent.ExpiredToken)
                }
            }
            _transactionState.value = transactionState.value.copy(isLoading = false)
        }

    }

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

            is TransactionEvent.GetTransactionListFromServer -> getTransactionList()
            is TransactionEvent.PostIncreaseBalanceToServer -> postIncreaseBalance()
        }
    }
}
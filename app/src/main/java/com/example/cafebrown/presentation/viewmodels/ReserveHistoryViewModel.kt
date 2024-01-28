package com.example.cafebrown.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cafebrown.presentation.states.ReserveHistoryState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetReserveHistoryListUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ReserveHistoryEvent
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReserveHistoryViewModel @Inject constructor(
    private val getReserveHistoryListUseCase: GetReserveHistoryListUseCase
) : ViewModel() {
    private val _reserveHistoryState = mutableStateOf(ReserveHistoryState())
    val reserveHistoryState : State<ReserveHistoryState> = _reserveHistoryState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ReserveHistoryEvent){
        when(event){
            ReserveHistoryEvent.GetListFromServer -> {
                getReserveHistoryList()
            }
        }
    }

    private fun getReserveHistoryList(){
        _reserveHistoryState.value = reserveHistoryState.value.copy(
            isLoading = true,
            response = Resource.Loading()
        )
        viewModelScope.launch {
            _reserveHistoryState.value = reserveHistoryState.value.copy(
                response = getReserveHistoryListUseCase.execute()
            )
            when (reserveHistoryState.value.response.data?.status) {

                JSonStatusCode.BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(reserveHistoryState.value.response.data?.message!!),
                            needAction = true
                        )
                    )
                }
                JSonStatusCode.SUCCESS -> {
                    _reserveHistoryState.value = reserveHistoryState.value.copy(
                        reserveList = reserveHistoryState.value.response.data?.data!!
                    )
                }
                JSonStatusCode.INTERNET_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem),
                            needAction = true
                        )
                    )
                }
                JSonStatusCode.SERVER_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem),
                            needAction = true
                        )
                    )
                }
                JSonStatusCode.EXPIRED_TOKEN -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ExpiredToken
                    )
                }
            }
            _reserveHistoryState.value = reserveHistoryState.value.copy(
                isLoading = false
            )
        }
    }
}
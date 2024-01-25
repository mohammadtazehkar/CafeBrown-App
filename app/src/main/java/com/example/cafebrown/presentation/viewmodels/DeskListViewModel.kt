package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetDeskListDataUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.DeskListEvent
import com.example.cafebrown.presentation.events.HomeEvent
import com.example.cafebrown.presentation.states.DeskState
import com.example.cafebrown.presentation.states.HomeState
import com.example.cafebrown.ui.screens.DeckItemData
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeskListViewModel @Inject constructor (
    private val getDeskListDataUseCase: GetDeskListDataUseCase
) : ViewModel() {
    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private val _deskListState = mutableStateOf(
        DeskState(
            response = Resource.Error("")
        )
    )

    val deskState: State<DeskState> = _deskListState

    fun onEvent (event : DeskListEvent){
        when(event){
            DeskListEvent.GetDeskList -> getDeskList()
        }
    }
    private fun getDeskList(){
        _deskListState.value = deskState.value.copy(
            response = Resource.Loading(),
            isLoading = true
        )

        viewModelScope.launch {
            _deskListState.value= deskState.value.copy(
                response = getDeskListDataUseCase.execute()
            )

            when (_deskListState.value.response.data?.status) {

                JSonStatusCode.BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(_deskListState.value.response.data?.message!!)
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {

                    _deskListState.value = deskState.value.copy(
                        response = _deskListState.value.response,
                        deskList = _deskListState.value.response.data?.data!!
                    )
                }

                JSonStatusCode.INTERNET_CONNECTION -> {
                    Log.i("meyti", "emit")
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }

                JSonStatusCode.SERVER_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }

            }
            _deskListState.value = deskState.value.copy(
                isLoading = false
            )
        }
    }
}
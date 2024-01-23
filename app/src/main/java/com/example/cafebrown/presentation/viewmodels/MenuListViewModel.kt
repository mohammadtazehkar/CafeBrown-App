package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetMenuListUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.MenuEvent
import com.example.cafebrown.presentation.states.MenuState
import com.example.cafebrown.utils.ArgumentKeys
import com.example.cafebrown.utils.JSonStatusCode.BAD_REQUEST
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SUCCESS
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuListViewModel @Inject constructor (
    private val getMenuListUseCase: GetMenuListUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _menuState = mutableStateOf(
        MenuState(
            response = Resource.Error(""),
            from = savedStateHandle.get<String>(ArgumentKeys.FROM)!!
        )
    )
    val menuState: State<MenuState> = _menuState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: MenuEvent) {
        when (event) {
            is MenuEvent.GetMenuList -> getMenuList()
        }
    }

    private fun getMenuList(){
        _menuState.value = menuState.value.copy(
            isLoading = true,
            response = Resource.Loading()
        )
        viewModelScope.launch {
            _menuState.value = menuState.value.copy(
                response = getMenuListUseCase.execute()
            )
            when(menuState.value.response.data?.status){
                BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(menuState.value.response.data?.message!!)
                        )
                    )
                }
                SUCCESS -> {
                    prepareListData()
                }
                SERVER_CONNECTION ->{
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }
                INTERNET_CONNECTION ->{
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }
                EXPIRED_TOKEN -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ExpiredToken
                    )
                }
            }
            _menuState.value = menuState.value.copy(
                isLoading = false
            )
        }
    }

    private fun prepareListData(){
        _menuState.value = menuState.value.copy(
            menuListState = menuState.value.response.data?.data!!
        )
    }
}
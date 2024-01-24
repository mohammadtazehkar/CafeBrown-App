package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetHomeDataUseCase
import com.example.cafebrown.domain.usecase.GetProfileDataUseCase
import com.example.cafebrown.domain.usecase.UpdateProfileDataUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.HomeEvent
import com.example.cafebrown.presentation.states.HomeState
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private val _homeState = mutableStateOf(
        HomeState(
            response = Resource.Error("")
        )
    )
    val homeState: State<HomeState> = _homeState
    fun onEvent (event : HomeEvent){
        when(event){
            HomeEvent.GetImageList -> getImageList()
        }
    }
    private fun getImageList() {
        _homeState.value = homeState.value.copy(
            response = Resource.Loading(),
            isLoading = true
        )
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                response = getHomeDataUseCase.execute()
            )

            when (_homeState.value.response.data?.status) {

                JSonStatusCode.BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(homeState.value.response.data?.message!!)
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {

                    Log.i("meyti",_homeState.value.response.data?.data?.imageList.toString() )
                    _homeState.value = homeState.value.copy(
                        imageList = _homeState.value.response.data?.data?.imageList!!
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
            _homeState.value = homeState.value.copy(
                isLoading = false
            )
        }
    }
}
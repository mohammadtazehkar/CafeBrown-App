package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


//@HiltViewModel
//class SplashViewModel @Inject constructor(private val checkUserExistUseCase: CheckUserExistUseCase) : ViewModel()  {
class SplashViewModel : ViewModel()  {

    private val _hasUserData = mutableStateOf(false)
    val hasUserData : State<Boolean> = _hasUserData

//    init {
//        checkData()
//    }

//    private fun checkData() = viewModelScope.launch {
//        _hasUserData.value = checkUserExistUseCase.execute()
//    }
}
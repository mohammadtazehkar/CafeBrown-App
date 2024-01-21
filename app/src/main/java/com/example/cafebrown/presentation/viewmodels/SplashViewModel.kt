package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.domain.usecase.CheckUserDataUseCase
import com.example.cafebrown.utils.Constants.USER_DATA_EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val checkUserDataUseCase: CheckUserDataUseCase): ViewModel()  {

    private val _userData = mutableStateOf(USER_DATA_EMPTY)
    val userData : State<Int> = _userData

    init {
        checkData()
    }

    private fun checkData() = viewModelScope.launch {
        _userData.value = checkUserDataUseCase.execute()
    }
}
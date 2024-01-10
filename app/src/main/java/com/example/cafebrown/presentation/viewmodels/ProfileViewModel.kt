package com.example.cafebrown.presentation.viewmodels

import android.text.format.DateUtils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProfileEvent
import com.example.cafebrown.presentation.states.ProfileState
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class ProfileViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _profileState = mutableStateOf(
        ProfileState(
            yearsList = (1320..1402).map { it.toString() },
            monthList = (1..12).map { it.toString() },
            daysList = (1..31).map { it.toString() }
//            response = Resource.Error("")
        )
    )
    val profileState: State<ProfileState> = _profileState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfileData -> TODO()
            is ProfileEvent.PrepareData -> TODO()
            is ProfileEvent.UpdateClicked -> TODO()
            is ProfileEvent.UpdateFirstNameState -> {
                _profileState.value = profileState.value.copy(
                    firstName = event.newValue
                )
            }
            is ProfileEvent.UpdateLastNameState -> {
                _profileState.value = profileState.value.copy(
                    lastName = event.newValue
                )
            }
            is ProfileEvent.UpdateGenderState -> {
                _profileState.value = profileState.value.copy(
                    gender = event.status
                )
            }
            is ProfileEvent.UpdateLoading -> TODO()
            is ProfileEvent.UpdateSelectedDay -> {
                _profileState.value = profileState.value.copy(
                    selectedDay = event.newValue
                )
            }
            is ProfileEvent.UpdateSelectedMonth -> {
                _profileState.value = profileState.value.copy(
                    selectedMonth = event.newValue
                )
                changeMonthsDays(event.newValue.toInt())
            }
            is ProfileEvent.UpdateSelectedYear -> {
                _profileState.value = profileState.value.copy(
                    selectedYear = event.newValue
                )
                checkLeapYear(event.newValue.toInt())
            }
        }
    }

    private fun checkLeapYear(newYear : Int){
        val isLeap = ( newYear % 33 == 1 || newYear % 33 == 5 || newYear % 33 == 9 || newYear % 33 == 13 ||
                newYear % 33 == 17 || newYear % 33 == 22 ||newYear % 33 == 26 ||newYear % 33 == 30)
        _profileState.value = profileState.value.copy(
            isLeap = isLeap
        )
        changeMonthsDays(profileState.value.selectedMonth.toInt())
    }

    private fun changeMonthsDays(selectedMonth: Int){
        if (selectedMonth < 7){
            val newDayList : List<String> =  (1..31).map { it.toString() }
            _profileState.value = profileState.value.copy(
                daysList = newDayList
            )
        }else{
            if ((selectedMonth == 12 && profileState.value.isLeap) || (selectedMonth != 12)){
                val newDayList : List<String> =  (1..30).map { it.toString() }
                _profileState.value = profileState.value.copy(
                    daysList = newDayList
                )

            }else{
                val newDayList : List<String> =  (1..29).map { it.toString() }
                _profileState.value = profileState.value.copy(
                    daysList = newDayList
                )

            }
        }
    }
}
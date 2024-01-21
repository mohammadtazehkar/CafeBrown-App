package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.data.models.profile.APIUpdateProfileRequest
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.domain.usecase.GetProfileDataUseCase
import com.example.cafebrown.domain.usecase.UpdateProfileDataUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProfileEvent
import com.example.cafebrown.presentation.states.ProfileState
import com.example.cafebrown.utils.ArgumentKeys.FROM
import com.example.cafebrown.utils.Destinations.VERIFY_SCREEN
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    private val getProfileDataUseCase: GetProfileDataUseCase,
    private val updateProfileDataUseCase: UpdateProfileDataUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _profileState = mutableStateOf(
        ProfileState(
            yearsList = (1320..1402).map { it.toString() },
            monthList = (1..12).map { it.toString() },
            daysList = (1..31).map { it.toString() },
            from = savedStateHandle.get<String>(FROM)!!,
//            dbInfo = PostVerificationCodeResponse(0,"","","",true,"",""),
            responseUpdate = Resource.Error("")
        )
    )
    val profileState: State<ProfileState> = _profileState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    init {
        setData()
    }
    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ActionClicked -> {
                signUp(event.onSignUpCompleted,event.onUpdateCompleted)
            }
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
            is ProfileEvent.UpdateLoading -> {
                _profileState.value = profileState.value.copy(
                    isLoading = event.status
                )
            }
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

    private fun setData(){

        viewModelScope.launch {
            val dbInfo = getProfileDataUseCase.execute()
            val dbBirthDate = dbInfo.birthDate
            _profileState.value = profileState.value.copy(
                firstName = dbInfo.firstName,
                lastName = dbInfo.lastName,
                mobileNumber = dbInfo.mobile,
                gender =dbInfo.sex,
                selectedYear = if (!dbBirthDate.isNullOrEmpty()){ dbBirthDate.split("/")[2] }else{ "1320" },
                selectedMonth = if (!dbBirthDate.isNullOrEmpty()){ dbBirthDate.split("/")[1] }else{ "1" },
                selectedDay = if (!dbBirthDate.isNullOrEmpty()){ dbBirthDate.split("/")[0] }else{ "1" }
            )
        }
        Log.i("mamali",profileState.value.selectedDay)


        checkLeapYear(profileState.value.selectedYear.toInt())
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
    private fun signUp(
        onSignUpCompleted: () -> Unit,
        onUpdateCompleted: () -> Unit
    ){
        if (isValidInputs()){
            _profileState.value = profileState.value.copy(
                responseUpdate = Resource.Loading()
            )
            viewModelScope.launch {
                _profileState.value = profileState.value.copy(
                    responseUpdate = updateProfileDataUseCase.execute(
                        APIUpdateProfileRequest(
                            profileState.value.firstName,
                            profileState.value.lastName,
                            profileState.value.selectedYear+"/"+profileState.value.selectedMonth+"/"+profileState.value.selectedDay,
                            profileState.value.gender,
                        )
                    )
                )
                if (_profileState.value.responseUpdate.data?.status == JSonStatusCode.BAD_REQUEST) {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(profileState.value.responseUpdate.data?.message!!)
                        )
                    )
                } else if (_profileState.value.responseUpdate.data?.status == JSonStatusCode.SUCCESS) {
                    if (profileState.value.from == VERIFY_SCREEN){
                        onSignUpCompleted()
                    }else{
                        onUpdateCompleted()
                    }
                }
            }
        }

    }

    private fun isValidInputs():Boolean{
        if (profileState.value.firstName.isEmpty()) {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.empty_first_name,
                            _profileState.value.firstName
                        )
                    )
                )
            }
            return false
        }
        else if (profileState.value.lastName.isEmpty()) {
            viewModelScope.launch {
                _uiEventFlow.emit(
                    AppUIEvent.ShowMessage(
                        message = UIText.StringResource(
                            resId = R.string.empty_last_name,
                            _profileState.value.lastName
                        )
                    )
                )
            }
            return false
        }
        else{
            return true
        }

    }
}
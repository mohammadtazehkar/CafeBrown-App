package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.data.models.aboutUs.APIPostComplaintsRequest
import com.example.cafebrown.domain.usecase.GetCoffeeShopDataUseCase
import com.example.cafebrown.domain.usecase.PostComplaintsUseCase
import com.example.cafebrown.presentation.events.AboutUsEvent
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.states.AboutUsState
import com.example.cafebrown.utils.JSonStatusCode
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants.ADDRESS
import com.example.cafebrown.utils.ServerConstants.DESCRIPTION
import com.example.cafebrown.utils.ServerConstants.INSTAGRAM
import com.example.cafebrown.utils.ServerConstants.SUPPORT_NUMBER
import com.example.cafebrown.utils.ServerConstants.TELEGRAM
import com.example.cafebrown.utils.ServerConstants.WIFI
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    private val getCoffeeShopDataUseCase: GetCoffeeShopDataUseCase,
    private val postComplaintsUseCase: PostComplaintsUseCase
) :
    ViewModel() {
    private var _aboutUsState = mutableStateOf(AboutUsState())
    var aboutUsState: State<AboutUsState> = _aboutUsState

    private var _aboutUsSharedFlow = MutableSharedFlow<AppUIEvent>()
    val aboutUsSharedFlow: SharedFlow<AppUIEvent> = _aboutUsSharedFlow.asSharedFlow()

    private fun getCoffeeShopData() {
        _aboutUsState.value =
            aboutUsState.value.copy(isLoading = true, responseCoffeShopData = Resource.Loading())
        viewModelScope.launch {
            _aboutUsState.value =
                aboutUsState.value.copy(responseCoffeShopData = getCoffeeShopDataUseCase.execute())
            when (_aboutUsState.value.responseCoffeShopData.data?.status) {
                JSonStatusCode.BAD_REQUEST -> {
                    _aboutUsSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.DynamicString(
                                _aboutUsState.value.responseCoffeShopData.data?.message!!
                            )
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {
                    val coffeeShopDataList =
                        _aboutUsState.value.responseCoffeShopData.data?.data?.coffeeShopDataList!!

                    coffeeShopDataList.forEach { coffeeShopData ->
                        when (coffeeShopData?.key) {
                            DESCRIPTION -> _aboutUsState.value = aboutUsState.value.copy(
                                aboutCafe = coffeeShopData.value!!
                            )

                            SUPPORT_NUMBER -> _aboutUsState.value = aboutUsState.value.copy(
                                cafePhone = coffeeShopData.value!!
                            )

                            ADDRESS -> _aboutUsState.value = aboutUsState.value.copy(
                                cafeAddress = coffeeShopData.value!!
                            )

                            WIFI -> _aboutUsState.value = aboutUsState.value.copy(
                                cafeWifiPassword = coffeeShopData.value!!
                            )

                            TELEGRAM -> _aboutUsState.value = aboutUsState.value.copy(
                                cafeTelegram = coffeeShopData.value!!
                            )

                            INSTAGRAM -> _aboutUsState.value = aboutUsState.value.copy(
                                cafeInstagram = coffeeShopData.value!!
                            )
                        }
                    }

                }

                JSonStatusCode.INTERNET_CONNECTION -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.internet_connection_problem)))
                }

                JSonStatusCode.SERVER_CONNECTION -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.connection_problem)))
                }

                JSonStatusCode.EXPIRED_TOKEN -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ExpiredToken)
                }
            }
            _aboutUsState.value = aboutUsState.value.copy(isLoading = false)
        }

    }

    private fun postComplaints() {
        _aboutUsState.value =
            aboutUsState.value.copy(
                isComplaintsRequest = true,
                isLoading = true,
                responseComplaints = Resource.Loading()
            )
        viewModelScope.launch {
            _aboutUsState.value = aboutUsState.value.copy(
                responseComplaints = postComplaintsUseCase.execute(
                    APIPostComplaintsRequest(
                        _aboutUsState.value.complaintData
                    )
                )
            )
            when (_aboutUsState.value.responseComplaints.data?.status) {
                JSonStatusCode.BAD_REQUEST -> {
                    _aboutUsSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.DynamicString(
                                _aboutUsState.value.responseComplaints.data?.message!!
                            )
                        )
                    )
                }

                JSonStatusCode.SUCCESS -> {
                    _aboutUsSharedFlow.emit(
                        AppUIEvent.ShowMessage(
                            UIText.StringResource(R.string.post_complaint_successfully),
                            isError = false
                        )
                    )
                    _aboutUsState.value =
                        aboutUsState.value.copy(isComplaintsRequest = false, complaintData = "" )
                }

                JSonStatusCode.INTERNET_CONNECTION -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.internet_connection_problem)))
                }

                JSonStatusCode.SERVER_CONNECTION -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ShowMessage(UIText.StringResource(R.string.connection_problem)))
                }

                JSonStatusCode.EXPIRED_TOKEN -> {
                    _aboutUsSharedFlow.emit(AppUIEvent.ExpiredToken)
                }
            }
            _aboutUsState.value = aboutUsState.value.copy(isLoading = false)
        }

    }

    fun onEvent(event: AboutUsEvent) {
        when (event) {
            is AboutUsEvent.ChangeComplaintData -> {
                _aboutUsState.value = aboutUsState.value.copy(
                    complaintData = event.text
                )
            }

            is AboutUsEvent.ChangeComplaintDialogVisibility -> {
                _aboutUsState.value = aboutUsState.value.copy(
                    complaintDialogVisibility = event.status
                )
            }

            is AboutUsEvent.ChangeRulesDialogVisibility -> {
                _aboutUsState.value = aboutUsState.value.copy(
                    rulesDialogVisibility = event.status
                )
            }

            AboutUsEvent.GetCoffeeShopDataFromServer -> getCoffeeShopData()
            AboutUsEvent.PostComplaintsToServer -> postComplaints()
        }
    }
}
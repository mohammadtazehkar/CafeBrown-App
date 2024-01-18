package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AboutUsEvent
import com.example.cafebrown.presentation.states.AboutUsState
import com.example.cafebrown.utils.UIText

class AboutUsViewModel : ViewModel() {
    private var _aboutUsState = mutableStateOf(
        AboutUsState(
            aboutCafe = UIText.StringResource(resId = R.string.lorem_ipsum),
            cafeAddress = UIText.StringResource(resId = R.string.coffee_address),
            cafePhone = "+989358377257",
            cafeTelegram = "https://t.me/shahinfasihi",
            cafeInstagram = "https://www.instagram.com/shahinfasihi",
            complaintDialogVisibility = false,
            complaintData = "",
            cafeWifiSSID = "mamalimamali",
            cafeWifiPassword = "12301230",
            rulesDialogVisibility = false,
        )
    )
    var aboutUsState: State<AboutUsState> = _aboutUsState

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
                if (!event.status) {
                    _aboutUsState.value = aboutUsState.value.copy(
                        complaintData = ""
                    )
                }
            }

            is AboutUsEvent.ChangeRulesDialogVisibility -> {
                _aboutUsState.value = aboutUsState.value.copy(
                    rulesDialogVisibility = event.status
                )
            }
        }
    }
}
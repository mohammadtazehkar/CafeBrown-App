package com.example.cafebrown.presentation.states

import com.example.cafebrown.utils.UIText

data class AboutUsState(
    var aboutCafe: UIText,
    var cafeAddress: UIText,
    var cafePhone: String,
    var cafeTelegram: String,
    var cafeInstagram: String,
    var complaintDialogVisibility: Boolean,
    var complaintData: String,
    var cafeWifiSSID: String,
    var cafeWifiPassword: String,
    var rulesDialogVisibility: Boolean
)

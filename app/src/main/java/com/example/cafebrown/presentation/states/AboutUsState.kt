package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.aboutUs.APIGetCoffeeShopDataResponse
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText

data class AboutUsState(
    var aboutCafe: String = "",
    var cafeAddress: String = "",
    var cafePhone: String = "",
    var cafeTelegram: String = "",
    var cafeInstagram: String = " ",
    var complaintDialogVisibility: Boolean = false,
    var complaintData: String = "",
    var cafeWifiSSID: String = "mohammadTazehkar",
    var cafeWifiPassword: String = "ramz123456",
    var rulesDialogVisibility: Boolean = false,
    var isLoading: Boolean = false,
    var responseCoffeShopData: Resource<APIGetCoffeeShopDataResponse> = Resource.Error(""),
    var responseComplaints: Resource<APIGlobalResponse> = Resource.Error(""),
    var isComplaintsRequest: Boolean = false // for knowing which request is active right now
)

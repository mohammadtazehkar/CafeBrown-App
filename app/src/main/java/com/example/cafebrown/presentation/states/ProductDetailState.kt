package com.example.cafebrown.presentation.states

import androidx.compose.runtime.MutableState

data class ProductDetailState(
    var isLoading: Boolean = false,
    var productId: Int,
    var productStarsList: MutableList<MutableState<Boolean>>,
    var userStarsList: MutableList<MutableState<Boolean>>,
    var productTitle: String,
    var productDescription: String = "",
    var productInstruction: String = "",
    var userComment: String = "",
    var imageList: List<String>
//    var response : Resource<APISignInResponse>
)

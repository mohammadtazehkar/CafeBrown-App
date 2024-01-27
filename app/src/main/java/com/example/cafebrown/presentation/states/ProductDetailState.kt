package com.example.cafebrown.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.cafebrown.data.models.productDetail.APIGetCommentResponse
import com.example.cafebrown.data.models.productDetail.APIGetProductDetailResponse
import com.example.cafebrown.data.models.productDetail.GetCommentResponse
import com.example.cafebrown.utils.Resource

data class ProductDetailState(
    var isLoading: Boolean = false,
    var hasRunEffect: Boolean = false,
    var commentListDialogVisibility: Boolean = false,
    var productId: Int,
    var productStarsList: MutableList<MutableState<Boolean>> = mutableStateListOf(
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false)
    ),
    var userStarsList: MutableList<MutableState<Boolean>> = mutableStateListOf(
        mutableStateOf(true),
        mutableStateOf(true),
        mutableStateOf(false),
        mutableStateOf(false),
        mutableStateOf(false)
    ),
    var productTitle: String,
    var productDescription: String = "",
    var productInstruction: String = "",
    var userComment: String = "",
    var imageList: List<String> = emptyList(),
    var commentList: List<GetCommentResponse> = emptyList(),
    var response: Resource<APIGetProductDetailResponse> = Resource.Error(""),
    var responseCommentList: Resource<APIGetCommentResponse> = Resource.Error("")
)

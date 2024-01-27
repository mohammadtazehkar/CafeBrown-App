package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetCommentListUseCase
import com.example.cafebrown.domain.usecase.GetProductDetailDataUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductDetailEvent
import com.example.cafebrown.presentation.states.ProductDetailState
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_ID
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_TITLE
import com.example.cafebrown.utils.JSonStatusCode.BAD_REQUEST
import com.example.cafebrown.utils.JSonStatusCode.EXPIRED_TOKEN
import com.example.cafebrown.utils.JSonStatusCode.INTERNET_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SERVER_CONNECTION
import com.example.cafebrown.utils.JSonStatusCode.SUCCESS
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.UIText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailDataUseCase: GetProductDetailDataUseCase,
    private val getCommentListUseCase: GetCommentListUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _productDetailState = mutableStateOf(
        ProductDetailState(
            productId = savedStateHandle.get<Int>(PRODUCT_ID)!!,
            productTitle = savedStateHandle.get<String>(PRODUCT_TITLE)!!,
        )
    )
    val productDetailState: State<ProductDetailState> = _productDetailState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.UpdateStars -> {
                if (event.status) {
                    _productDetailState.value.userStarsList.forEachIndexed { index, _ ->
                        _productDetailState.value.userStarsList[index].value = index <= event.index

                    }
                } else {
                    _productDetailState.value.userStarsList.forEachIndexed { index, _ ->
                        _productDetailState.value.userStarsList[index].value = index < event.index

                    }
                }
            }
            is ProductDetailEvent.UpdateUserComment -> {
                _productDetailState.value = productDetailState.value.copy(
                    userComment = event.newValue
                )
            }
            is ProductDetailEvent.GetDataFromServer -> {
                getProductDetailFromServer()
            }
            is ProductDetailEvent.UpdateHasRunEffect -> {
                _productDetailState.value = productDetailState.value.copy(
                    hasRunEffect = event.status
                )
            }
            is ProductDetailEvent.GetCommentListFromServer -> {
                getCommentList()

            }
            is ProductDetailEvent.UpdateCommentDialogVisibility -> {
                _productDetailState.value = productDetailState.value.copy(
                    commentListDialogVisibility = event.status
                )
            }
        }
    }

    private fun getProductDetailFromServer() {
        _productDetailState.value = productDetailState.value.copy(
            isLoading = true,
            response = Resource.Loading()
        )
        viewModelScope.launch {
            _productDetailState.value = productDetailState.value.copy(
                response = getProductDetailDataUseCase.execute(productDetailState.value.productId)
            )
            when (productDetailState.value.response.data?.status) {
                BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(productDetailState.value.response.data?.message!!),
                            needAction = true
                        )
                    )
                }

                SUCCESS -> {
                    prepareProductData()
                }

                INTERNET_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem),
                            needAction = true
                        )
                    )
                }

                SERVER_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem),
                            needAction = true
                        )
                    )
                }

                EXPIRED_TOKEN -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ExpiredToken
                    )
                }
            }
            _productDetailState.value = productDetailState.value.copy(
                isLoading = false
            )
        }
    }

    private fun prepareProductData() {
        val responseData = productDetailState.value.response.data?.data
        _productDetailState.value = productDetailState.value.copy(
            productDescription = responseData?.description!!,
            productInstruction = responseData.tutorial,
            imageList = responseData.productImageList
        )
        if (responseData.rating != 0) {
            prepareProductStars(responseData.rating - 1)
        }
    }

    private fun prepareProductStars(starIndex: Int) {
        _productDetailState.value.userStarsList.forEachIndexed { index, _ ->
            _productDetailState.value.userStarsList[index].value = index <= starIndex
        }
    }

    private fun getCommentList(){
        _productDetailState.value = productDetailState.value.copy(
            isLoading = true,
            responseCommentList = Resource.Loading()
        )
        viewModelScope.launch {
            _productDetailState.value = productDetailState.value.copy(
                responseCommentList = getCommentListUseCase.execute(productDetailState.value.productId)
            )
            when(productDetailState.value.responseCommentList.data?.status){
                BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(productDetailState.value.response.data?.message!!)
                        )
                    )
                }
                SUCCESS -> {
                    val commentsResponse = productDetailState.value.responseCommentList.data?.data
                    if (commentsResponse.isNullOrEmpty()){
                        _uiEventFlow.emit(
                            AppUIEvent.ShowMessage(
                                message = UIText.StringResource(R.string.empty_comment_list)
                            )
                        )
                    }else{
                        _productDetailState.value = productDetailState.value.copy(
                            commentList = commentsResponse,
                            commentListDialogVisibility = true
                        )
                    }
                }
                INTERNET_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }
                SERVER_CONNECTION -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }
                EXPIRED_TOKEN -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ExpiredToken
                    )
                }
            }
            _productDetailState.value = productDetailState.value.copy(
                isLoading = false
            )
        }
    }
}
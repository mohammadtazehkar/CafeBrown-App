package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductDetailEvent
import com.example.cafebrown.presentation.states.ProductDetailState
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_ID
import com.example.cafebrown.utils.ArgumentKeys.PRODUCT_TITLE
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class ProductDetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _productDetailState = mutableStateOf(
        ProductDetailState(
            productId = savedStateHandle.get<Int>(PRODUCT_ID)!!,
            productTitle = savedStateHandle.get<String>(PRODUCT_TITLE)!!,
            productStarsList = mutableStateListOf(
                mutableStateOf(true) , mutableStateOf(true), mutableStateOf(true), mutableStateOf(true), mutableStateOf(false)
            ),
            userStarsList = mutableStateListOf(
                mutableStateOf(true) , mutableStateOf(true), mutableStateOf(false), mutableStateOf(false), mutableStateOf(false)
            )
//            response = Resource.Error("")
        )
    )
    val productDetailState: State<ProductDetailState> = _productDetailState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ProductDetailEvent) {
        when (event) {
            is ProductDetailEvent.UpdateLoading -> TODO()
            is ProductDetailEvent.UpdateStars -> {
                if (event.status) {
                    _productDetailState.value.userStarsList.forEachIndexed { index, b ->
                        _productDetailState.value.userStarsList[index].value = index <= event.index

                    }
                } else {
                    _productDetailState.value.userStarsList.forEachIndexed { index, b ->
                        _productDetailState.value.userStarsList[index].value = index < event.index

                    }
                }
            }

            is ProductDetailEvent.UpdateUserComment -> {
                _productDetailState.value = productDetailState.value.copy(
                    userComment = event.newValue
                )
            }
        }
    }
}
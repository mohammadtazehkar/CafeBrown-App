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
            ),
            imageList = mutableListOf(
                "https://img.freepik.com/free-psd/house-moving-service-banner-template_23-2148966110.jpg?w=1380&t=st=1704037972~exp=1704038572~hmac=d20ba2d1e20c11db75d1141d159975dc413da47f9d4913ae796059be26cf3a24",
                "https://img.freepik.com/free-psd/cleaning-service-concept-landing-page-template_23-2148623576.jpg?w=1380&t=st=1704038752~exp=1704039352~hmac=03bccd2338e5c8cc6faea02f07ee299866f936819c501af04a342b1ee7e09dea",
                "https://img.freepik.com/free-vector/flat-repair-shop-business-social-media-promo-template_23-2149534782.jpg?w=1380&t=st=1704038068~exp=1704038668~hmac=916128a6aac0696959245972dcd258490622583e9d64e10e91369b2a8df9f921",
                "https://img.freepik.com/free-psd/electrical-services-banner-design_23-2148652456.jpg?w=1380&t=st=1704038172~exp=1704038772~hmac=88bc6d0f0992632104abf9da73d02959efb6032f670c1d0b63462456dd3e20c4",
                "https://img.freepik.com/free-psd/professional-plumbers-job-banner-template_23-2148709811.jpg?w=1380&t=st=1704038299~exp=1704038899~hmac=d9382f277c9d2c1c57053d4f76c05e455c282db92d1bd3a4ad420f0eefff414e",
                "https://img.freepik.com/free-vector/minimal-architecture-project-sale-banner_23-2149447727.jpg?w=1380&t=st=1704038426~exp=1704039026~hmac=6a1fd38e2d8a4ea2606495d2a9760559aa7bd029e2129d0a388b42dd55b69c00"
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
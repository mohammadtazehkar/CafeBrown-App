package com.example.cafebrown.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafebrown.R
import com.example.cafebrown.domain.usecase.GetSubMenuAndProductListUseCase
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.states.ProductListState
import com.example.cafebrown.utils.ArgumentKeys
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
class ProductListViewModel @Inject constructor  (
    private val getSubMenuAndProductListUseCase: GetSubMenuAndProductListUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _productListState = mutableStateOf(
        ProductListState(
            selectedCategoryId = savedStateHandle.get<Int>(ArgumentKeys.CATEGORY_ID)!!,
            from = savedStateHandle.get<String>(ArgumentKeys.FROM)!!,
            selectedCount = 0,
            total = 10,
            response = Resource.Error("")
        )
    )
    val productListState: State<ProductListState> = _productListState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ProductListEvent) {
        when(event){
            is ProductListEvent.SelectSubCategory -> {
                _productListState.value = productListState.value.copy(
                    selectedCategoryId = event.subCategoryId
                )
                getSubCategoryAndProductList(true)
            }
            is ProductListEvent.DeCreaseCount -> {
                if (_productListState.value.selectedCount !=0){
                    _productListState.value = productListState.value.copy(
                        selectedCount = productListState.value.selectedCount - 1
                    )
                }
            }
            is ProductListEvent.InCreaseCount -> {
                if (_productListState.value.total > _productListState.value.selectedCount){
                    _productListState.value = productListState.value.copy(
                        selectedCount = productListState.value.selectedCount + 1
                    )
                }
            }
            is ProductListEvent.GetListFromServer -> {
                getSubCategoryAndProductList()
            }
            is ProductListEvent.UpdateHasRunEffect -> {
                _productListState.value = productListState.value.copy(
                    hasRunEffect = event.status
                )
            }
        }
    }

    private fun getSubCategoryAndProductList(isFromSubCategory: Boolean = false){
        _productListState.value = productListState.value.copy(
            isLoading = true,
            response = Resource.Loading()
        )
        viewModelScope.launch {
            _productListState.value = productListState.value.copy(
                response = getSubMenuAndProductListUseCase.execute(productListState.value.selectedCategoryId)
            )
            when (productListState.value.response.data?.status){
                BAD_REQUEST -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.DynamicString(productListState.value.response.data?.message!!)
                        )
                    )
                }
                SUCCESS -> {
                    prepareSubCategoryAndProductList(isFromSubCategory)
                }
                SERVER_CONNECTION ->{
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.connection_problem)
                        )
                    )
                }
                INTERNET_CONNECTION ->{
                    _uiEventFlow.emit(
                        AppUIEvent.ShowMessage(
                            message = UIText.StringResource(R.string.internet_connection_problem)
                        )
                    )
                }
                EXPIRED_TOKEN -> {
                    _uiEventFlow.emit(
                        AppUIEvent.ExpiredToken
                    )
                }
            }
            _productListState.value = productListState.value.copy(
                isLoading = false
            )
        }
    }

    private fun prepareSubCategoryAndProductList(isFromSubCategory: Boolean = false){
        if (isFromSubCategory){
            _productListState.value = productListState.value.copy(
                productListState = productListState.value.response.data?.data?.productList!!,
            )
        }else{
            _productListState.value = productListState.value.copy(
                productListState = productListState.value.response.data?.data?.productList!!,
                subCategoryListState = productListState.value.response.data?.data?.subMenuList!!
            )
        }
    }
}
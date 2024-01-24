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
//            productListState = listOf(
//                ProductListItemData(
//                    1, "قهوه","75",1, R.drawable.coffee_cup,10
//                ),
//                ProductListItemData(
//                    2, "چای و دمنوش", "75",1, R.drawable.tea, 15
//                ),
//                ProductListItemData(
//                    3, "نوشیدنی های گرم","75",1,  R.drawable.coffee_break, 64
//                ),
//                ProductListItemData(
//                    4, "نوشیدنی های سرد","75",1,  R.drawable.cool, 54
//                ),
//                ProductListItemData(
//                    5, "بستنی","75",1,  R.drawable.ice_cream,120
//                ),
//                ProductListItemData(
//                    6, "کیک و دسر","75",1,  R.drawable.muffin, 564
//                ),
//                ProductListItemData(
//                    7, "میان وعده و غذا","75",1,  R.drawable.fast_food,12
//                ),
//                ProductListItemData(
//                    8, "صبحانه","75",1,  R.drawable.breakfast,54
//                ),
//                ProductListItemData(
//                    9, "میان وعده و غذا","75",1,  R.drawable.fast_food,1
//                ),
//                ProductListItemData(
//                    10, "صبحانه","75",1,  R.drawable.breakfast,8
//                )
//            ),
//            subCategoryListState = listOf(
//                SubCategoryItemData(1,"زیر منو 1",true),
//                SubCategoryItemData(2,"زیر منو 2",false),
//                SubCategoryItemData(3,"زیر منو 3",false),
//                SubCategoryItemData(4,"زیر منو 4",false),
//                SubCategoryItemData(5,"زیر منو 5",false),
//                SubCategoryItemData(6,"زیر منو 6",false),
//                SubCategoryItemData(7,"زیر منو 7",false),
//                SubCategoryItemData(8,"زیر منو 8",false),
//            ),
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
//                var newLists = productListState.value.subCategoryListState
//                val oldSelectedIndex = productListState.value.subCategoryListState.indexOfFirst { x -> x.isSelected }
//                newLists[oldSelectedIndex].isSelected = false
//                val newSelectedIndex = productListState.value.subCategoryListState.indexOfFirst { x -> x.id == event.subCategoryId }
//                newLists[newSelectedIndex].isSelected = true
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
                    Log.i("myTag", productListState.value.selectedCount.toString())
                    _productListState.value = productListState.value.copy(
                        selectedCount = productListState.value.selectedCount + 1
                    )
                }
            }
            is ProductListEvent.GetListFromServer -> {
                getSubCategoryAndProductList()
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
        Log.i("mamali","${productListState.value.selectedCategoryId}")
        if (isFromSubCategory){
            val selectedIndex = productListState.value.subCategoryListState.indexOfFirst { x -> x.id == productListState.value.selectedCategoryId }
            _productListState.value.subCategoryListState[selectedIndex].isSelected = true
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
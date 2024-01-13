package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.states.ProductListState
import com.example.cafebrown.ui.screens.ProductListItemData
import com.example.cafebrown.ui.screens.SubCategoryItemData
import com.example.cafebrown.utils.ArgumentKeys
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

//@HiltViewModel
//class SignInViewModel @Inject constructor (private val signInUseCase: SignInUseCase) : ViewModel() {
class ProductListViewModel (
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _productListState = mutableStateOf(
        ProductListState(
            selectedCategoryId = savedStateHandle.get<Int>(ArgumentKeys.CATEGORY_ID)!!,
            productListState = listOf(
                ProductListItemData(
                    1, "قهوه","75",1, R.drawable.coffee_cup
                ),
                ProductListItemData(
                    2, "چای و دمنوش", "75",1, R.drawable.tea
                ),
                ProductListItemData(
                    3, "نوشیدنی های گرم","75",1,  R.drawable.coffee_break
                ),
                ProductListItemData(
                    4, "نوشیدنی های سرد","75",1,  R.drawable.cool
                ),
                ProductListItemData(
                    5, "بستنی","75",1,  R.drawable.ice_cream
                ),
                ProductListItemData(
                    6, "کیک و دسر","75",1,  R.drawable.muffin
                ),
                ProductListItemData(
                    7, "میان وعده و غذا","75",1,  R.drawable.fast_food
                ),
                ProductListItemData(
                    8, "صبحانه","75",1,  R.drawable.breakfast
                ),
                ProductListItemData(
                    9, "میان وعده و غذا","75",1,  R.drawable.fast_food
                ),
                ProductListItemData(
                    10, "صبحانه","75",1,  R.drawable.breakfast
                )
            ),
            subCategoryListState = listOf(
                SubCategoryItemData(1,"زیر منو 1",true),
                SubCategoryItemData(2,"زیر منو 2",false),
                SubCategoryItemData(3,"زیر منو 3",false),
                SubCategoryItemData(4,"زیر منو 4",false),
                SubCategoryItemData(5,"زیر منو 5",false),
                SubCategoryItemData(6,"زیر منو 6",false),
                SubCategoryItemData(7,"زیر منو 7",false),
                SubCategoryItemData(8,"زیر منو 8",false),
            )
//            response = Resource.Error("")
        )
    )
    val productListState: State<ProductListState> = _productListState

    private val _uiEventFlow = MutableSharedFlow<AppUIEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    fun onEvent(event: ProductListEvent) {
        when(event){
            is ProductListEvent.UpdateLoading -> TODO()
            is ProductListEvent.SelectSubCategory -> {
                var newLists = productListState.value.subCategoryListState
                val oldSelectedIndex = productListState.value.subCategoryListState.indexOfFirst { x -> x.isSelected }
                newLists[oldSelectedIndex].isSelected = false
                val newSelectedIndex = productListState.value.subCategoryListState.indexOfFirst { x -> x.id == event.subCategoryId }
                newLists[newSelectedIndex].isSelected = true
                _productListState.value = productListState.value.copy(
                    subCategoryListState = newLists
                )
            }
        }
    }
}
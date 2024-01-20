package com.example.cafebrown.presentation.events

sealed class ProductListEvent{
    data class UpdateLoading(val status: Boolean) : ProductListEvent()
    data class SelectSubCategory(val subCategoryId: Int) : ProductListEvent()
    data object InCreaseCount : ProductListEvent()
    data object DeCreaseCount : ProductListEvent()
//    data object PrepareData : MenuCategoryEvent()

}

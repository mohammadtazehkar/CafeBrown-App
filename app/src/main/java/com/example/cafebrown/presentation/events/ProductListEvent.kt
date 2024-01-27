package com.example.cafebrown.presentation.events

sealed class ProductListEvent{
    data class SelectSubCategory(val subCategoryId: Int): ProductListEvent()
    data object GetListFromServer: ProductListEvent()
    data object InCreaseCount: ProductListEvent()
    data object DeCreaseCount: ProductListEvent()
    data class UpdateHasRunEffect(val status: Boolean): ProductListEvent()

}

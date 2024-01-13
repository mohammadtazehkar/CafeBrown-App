package com.example.cafebrown.presentation.states

import com.example.cafebrown.ui.screens.ProductListItemData
import com.example.cafebrown.ui.screens.SubCategoryItemData


data class ProductListState(
    var isLoading : Boolean = true,
    var selectedCategoryId: Int,
    var subCategoryListState : List<SubCategoryItemData> = mutableListOf(),
    var productListState : List<ProductListItemData> = mutableListOf(),
//    var response: Resource<APIHomeDataResponse>
)

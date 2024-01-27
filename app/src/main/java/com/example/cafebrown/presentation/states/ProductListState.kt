package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.product.APIGetSubMenuAndProductResponse
import com.example.cafebrown.data.models.product.ProductResponse
import com.example.cafebrown.data.models.product.SubMenuResponse
import com.example.cafebrown.utils.Resource


data class ProductListState(
    var isLoading : Boolean = true,
    var selectedCategoryId: Int,
    var subCategoryListState : MutableList<SubMenuResponse> = mutableListOf(),
    var productListState : List<ProductResponse> = emptyList(),
    var from : String,
    var total: Int,
    var selectedCount: Int,
    var hasRunEffect: Boolean = false,
    var response: Resource<APIGetSubMenuAndProductResponse>
)

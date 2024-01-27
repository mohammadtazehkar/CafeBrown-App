package com.example.cafebrown.presentation.events

sealed class ProductDetailEvent{
    data class UpdateStars(var index: Int, var status: Boolean): ProductDetailEvent()
    data class UpdateUserComment(var newValue: String): ProductDetailEvent()
    data object GetDataFromServer: ProductDetailEvent()
    data class UpdateHasRunEffect(val status: Boolean): ProductDetailEvent()
    data class UpdateCommentDialogVisibility(val status: Boolean): ProductDetailEvent()
    data object GetCommentListFromServer: ProductDetailEvent()

}

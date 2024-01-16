package com.example.cafebrown.presentation.events

sealed class ProductDetailEvent{
    data class UpdateLoading(var status: Boolean): ProductDetailEvent()
    data class UpdateStars(var index: Int, var status: Boolean): ProductDetailEvent()
    data class UpdateUserComment(var newValue: String): ProductDetailEvent()

}

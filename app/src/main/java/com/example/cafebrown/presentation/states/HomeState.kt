package com.example.cafebrown.presentation.states

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.data.models.home.APIGetHomeDataResponse
import com.example.cafebrown.data.models.home.HomeImage
import com.example.cafebrown.utils.Resource

data class HomeState(
    var isLoading: Boolean = false,
    var response: Resource<APIGetHomeDataResponse>,
    var imageList: List<HomeImage> = emptyList()
)
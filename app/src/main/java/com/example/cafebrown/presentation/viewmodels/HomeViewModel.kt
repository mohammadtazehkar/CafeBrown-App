package com.example.cafebrown.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cafebrown.presentation.states.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel

class HomeViewModel : ViewModel() {

    private val _homeState = mutableStateOf(
        HomeState(
            imageList = mutableListOf(
                "https://img.freepik.com/free-psd/house-moving-service-banner-template_23-2148966110.jpg?w=1380&t=st=1704037972~exp=1704038572~hmac=d20ba2d1e20c11db75d1141d159975dc413da47f9d4913ae796059be26cf3a24",
                "https://img.freepik.com/free-psd/cleaning-service-concept-landing-page-template_23-2148623576.jpg?w=1380&t=st=1704038752~exp=1704039352~hmac=03bccd2338e5c8cc6faea02f07ee299866f936819c501af04a342b1ee7e09dea",
                "https://img.freepik.com/free-vector/flat-repair-shop-business-social-media-promo-template_23-2149534782.jpg?w=1380&t=st=1704038068~exp=1704038668~hmac=916128a6aac0696959245972dcd258490622583e9d64e10e91369b2a8df9f921",
                "https://img.freepik.com/free-psd/electrical-services-banner-design_23-2148652456.jpg?w=1380&t=st=1704038172~exp=1704038772~hmac=88bc6d0f0992632104abf9da73d02959efb6032f670c1d0b63462456dd3e20c4",
                "https://img.freepik.com/free-psd/professional-plumbers-job-banner-template_23-2148709811.jpg?w=1380&t=st=1704038299~exp=1704038899~hmac=d9382f277c9d2c1c57053d4f76c05e455c282db92d1bd3a4ad420f0eefff414e",
                "https://img.freepik.com/free-vector/minimal-architecture-project-sale-banner_23-2149447727.jpg?w=1380&t=st=1704038426~exp=1704039026~hmac=6a1fd38e2d8a4ea2606495d2a9760559aa7bd029e2129d0a388b42dd55b69c00"
            )
        )
    )

    val homeState: State<HomeState> = _homeState
}
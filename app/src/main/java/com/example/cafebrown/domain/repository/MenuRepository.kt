package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.data.models.verify.PostVerificationCodeResponse
import com.example.cafebrown.utils.Resource

interface MenuRepository {

    suspend fun getMenuList(): Resource<APIGetMenuResponse>

}
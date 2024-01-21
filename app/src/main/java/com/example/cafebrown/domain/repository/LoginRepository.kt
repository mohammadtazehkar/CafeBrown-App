package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.APIGlobalResponse
import com.example.cafebrown.utils.Resource

interface LoginRepository {

    suspend fun postMobile(mobileNumber: String): Resource<APIGlobalResponse>
}
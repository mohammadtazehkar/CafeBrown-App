package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import retrofit2.Response

interface MenuRemoteDataSource {
    suspend fun getMenuList(token: String): Response<APIGetMenuResponse>
}
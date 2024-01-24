package com.example.cafebrown.data.repository.datasourceImpl

import com.example.cafebrown.data.api.APIService
import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.data.repository.datasource.DeskRemoteDataSource
import retrofit2.Response

class DeskRemoteDataSourceImpl(
    private val apiService: APIService):DeskRemoteDataSource {
    override suspend fun getDeskList(token: String): Response<APIGetDeskResponse> {
        return apiService.getDeskList(token)
    }
}
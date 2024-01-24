package com.example.cafebrown.data.repository.datasource

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import retrofit2.Response

interface DeskRemoteDataSource {
    suspend fun getDeskList(token : String) : Response<APIGetDeskResponse>
}
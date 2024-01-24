package com.example.cafebrown.domain.repository

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.utils.Resource

interface DeskRepository {
    suspend fun getDeskList(): Resource<APIGetDeskResponse>
}
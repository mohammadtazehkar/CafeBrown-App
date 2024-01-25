package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.desk.APIGetDeskResponse
import com.example.cafebrown.domain.repository.DeskRepository
import com.example.cafebrown.utils.Resource

class GetDeskListDataUseCase(private val deskRepository: DeskRepository) {
    suspend fun execute() : Resource<APIGetDeskResponse> = deskRepository.getDeskList()
}
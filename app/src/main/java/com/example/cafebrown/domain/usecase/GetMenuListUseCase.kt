package com.example.cafebrown.domain.usecase

import com.example.cafebrown.data.models.menu.APIGetMenuResponse
import com.example.cafebrown.domain.repository.MenuRepository
import com.example.cafebrown.utils.Resource

class GetMenuListUseCase(private val menuRepository: MenuRepository) {
    suspend fun execute(): Resource<APIGetMenuResponse> = menuRepository.getMenuList()

}
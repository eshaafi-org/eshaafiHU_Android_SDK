package com.example.eshaafi_healthunit.app.domain.home.usecases

import com.example.eshaafi_healthunit.app.domain.home.model.CityStatus
import com.example.eshaafi_healthunit.app.domain.home.model.HomeModel
import com.example.eshaafi_healthunit.app.domain.home.repository.HomeRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): CityStatus {
        return homeRepository.getItems()
    }
}


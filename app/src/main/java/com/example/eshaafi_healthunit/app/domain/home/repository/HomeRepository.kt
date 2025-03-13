package com.example.eshaafi_healthunit.app.domain.home.repository

import com.example.eshaafi_healthunit.app.domain.home.model.CityStatus

interface HomeRepository {
    suspend fun getItems(): CityStatus
}

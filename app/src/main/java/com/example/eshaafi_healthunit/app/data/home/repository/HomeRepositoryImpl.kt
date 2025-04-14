package com.example.eshaafi_healthunit.app.data.home.repository

import com.example.eshaafi_healthunit.app.data.home.api.HomeApi
import com.example.eshaafi_healthunit.app.domain.home.model.CitiesResponse
import com.example.eshaafi_healthunit.app.domain.home.model.CityStatus
import com.example.eshaafi_healthunit.app.domain.home.model.PakistanCities
import com.example.eshaafi_healthunit.app.domain.home.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {
    override suspend fun getItems(): CityStatus {
        val response = homeApi.getItems()
        return CityStatus(
            responseCode = response.responseCode,
            message = response.message,
            response = response.response?.let {
                CitiesResponse(
                    pakistanCities = it.pakistanCities.map { city ->
                        PakistanCities(
                            name = city.name,
                            country = city.country,
                            lng = city.lng,
                            lat = city.lat
                        )
                    } as ArrayList<PakistanCities>,
                    name = it.name,
                    type = it.type
                )
            }
        )
    }
}

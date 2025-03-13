package com.example.eshaafi_healthunit.app.data.home.api

import com.example.eshaafi_healthunit.app.data.home.model.CityStatusDto
import com.example.eshaafi_healthunit.app.utils.Constant.Companion.CITIES_API
import retrofit2.http.GET

interface HomeApi {
    @GET(CITIES_API) // Use only endpoint, as the base URL is already set
    suspend fun getItems(): CityStatusDto
}

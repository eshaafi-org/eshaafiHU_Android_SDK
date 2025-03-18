package com.example.eshaafihu_android_sdk.feature.cities.domain.entity

data class CitiesEntityResponseModel (

    val responseCode: Int?,
    val message: String?,
    val response: CitiesEntityResponse?

)

data class CitiesEntityResponse (

    val pakistanCities: List<PakistanEntityCitiesResponse>,
    val name: String?,
    val type: String?

)

data class PakistanEntityCitiesResponse (

    val name: String?,
    val country: String?,
    val lng: String?,
    val lat: String?

)
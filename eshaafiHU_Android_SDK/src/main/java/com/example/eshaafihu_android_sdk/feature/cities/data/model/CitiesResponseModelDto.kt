package com.example.eshaafihu_android_sdk.feature.cities.data.model

import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponseModel
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.CitiesEntityResponse
import com.example.eshaafihu_android_sdk.feature.cities.domain.entity.PakistanEntityCitiesResponse
import com.google.gson.annotations.SerializedName

// DTO for Cities API Response
internal data class CitiesResponseModelDto(
    @SerializedName("responseCode") val responseCode: Int? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("response") val response: CitiesEntityResponseDto? = null
) {
    /**
     * Converts DTO to Domain Entity
     */
    fun toDomain(): CitiesEntityResponseModel {
        return CitiesEntityResponseModel(
            responseCode = responseCode,
            message = message,
            response = response?.toDomain()
        )
    }
}

// DTO for Cities Entity Response
internal data class CitiesEntityResponseDto(
    @SerializedName("pakistan_cities") val pakistanCities: List<PakistanEntityCitiesResponseDto> = emptyList(),
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null
) {
    fun toDomain(): CitiesEntityResponse {
        return CitiesEntityResponse(
            pakistanCities = pakistanCities.map { it.toDomain() },
            name = name,
            type = type
        )
    }
}

// DTO for Pakistan Cities Response
internal data class PakistanEntityCitiesResponseDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("lng") val lng: String? = null,
    @SerializedName("lat") val lat: String? = null
) {
    fun toDomain(): PakistanEntityCitiesResponse {
        return PakistanEntityCitiesResponse(
            name = name,
            country = country,
            lng = lng,
            lat = lat
        )
    }
}

//Usage Examples for response Model
//✅ Convert API response DTO to Domain Entity
//
//val apiResponse: CitiesResponseModelDto = getCitiesFromApi()
//val domainModel: CitiesEntityResponseModel = apiResponse.toDomain()

//Usage Examples for request model
//✅ Convert Domain Entity to DTO (For API Request/DB Storage)
//
//val dtoModel: CitiesResponseModelDto = CitiesResponseModelDto.fromDomain(domainModel
package com.example.eshaafi_healthunit.app.data.home.model

import com.google.gson.annotations.SerializedName

data class HomeDto(
    val fact: String,
    val length: Int
)

data class CityStatusDto (

    @SerializedName("responseCode" ) var responseCode : Int?      = null,
    @SerializedName("message"      ) var message      : String?   = null,
    @SerializedName("response"     ) var response     : CitiesResponse? = CitiesResponse()

)

data class CitiesResponse (

    @SerializedName("pakistan_cities" ) var pakistanCities : ArrayList<PakistanCities> = arrayListOf(),
    @SerializedName("name"            ) var name           : String?                   = null,
    @SerializedName("type"            ) var type           : String?                   = null

)

data class PakistanCities (

    @SerializedName("name"    ) var name    : String? = null,
    @SerializedName("country" ) var country : String? = null,
    @SerializedName("lng"     ) var lng     : String? = null,
    @SerializedName("lat"     ) var lat     : String? = null

)

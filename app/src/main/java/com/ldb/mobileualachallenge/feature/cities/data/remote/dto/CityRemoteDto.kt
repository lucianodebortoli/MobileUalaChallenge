package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias CityRemoteDtoId = Long

@Serializable
data class CityRemoteDto(
    @SerialName("country") val countryPrefix: String,
    @SerialName("name") val name: String,
    @SerialName("_id") val id: CityRemoteDtoId,
    @SerialName("coord") val coordinates: Coordinates
) {
    @Serializable
    data class Coordinates(
        @SerialName("lon") val longitude: Double,
        @SerialName("lat") val latitude: Double
    )
}

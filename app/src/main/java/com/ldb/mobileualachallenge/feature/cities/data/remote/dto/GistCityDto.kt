package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import com.squareup.moshi.Json

typealias GistCityDtoId = Long

data class GistCityDto(
    @param:Json(name = "country") val countryPrefix: String,
    @param:Json(name = "name") val name: String,
    @param:Json(name = "_id") val id: GistCityDtoId,
    @param:Json(name = "coord") val coordinates: Coordinates
) {
    data class Coordinates(
        @param:Json(name = "lon") val longitude: Double,
        @param:Json(name = "lat") val latitude: Double
    )
}

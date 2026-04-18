package com.ldb.mobileualachallenge.feature.cities.domain.model

typealias CityId = Long

data class City(
    val id: CityId,
    val countryPrefix: String,
    val name: String,
    val coordinates: Coordinates,
    val isFavorite: Boolean
) {
    data class Coordinates(
        val latitude: Double,
        val longitude: Double
    )
}
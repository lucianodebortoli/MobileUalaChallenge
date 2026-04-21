package com.ldb.mobileualachallenge.feature.cities.domain.model

import com.ldb.mobileualachallenge.core.domain.model.CoreCoordinates

typealias CityId = Long

data class City(
    val id: CityId,
    val countryPrefix: String,
    val name: String,
    val coordinates: CoreCoordinates,
    val isFavorite: Boolean
)
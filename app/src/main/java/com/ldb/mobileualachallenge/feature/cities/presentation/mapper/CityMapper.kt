package com.ldb.mobileualachallenge.feature.cities.presentation.mapper

import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData

fun City.toItemData(): CityListItemData = CityListItemData(
    id = id,
    title = "$name - ${countryPrefix.uppercase()}",
    subtitle = coordinates.asString(),
    isFavorite = isFavorite
)

private fun City.Coordinates.asString() = "${"%.4f".format(latitude)}°, ${"%.4f".format(longitude)}°"
package com.ldb.mobileualachallenge.feature.cities.presentation.mapper

import com.ldb.mobileualachallenge.core.domain.model.CoreCoordinates
import com.ldb.mobileualachallenge.core.domain.model.CoreMarker
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData

fun City.toItemData(): CityListItemData = CityListItemData(
    id = id,
    title = "$name - ${countryCode.uppercase()}",
    subtitle = coordinates.asString(),
    isFavorite = isFavorite
)

fun CoreCoordinates.asString() = "${"%.4f".format(latitude)}°, ${"%.4f".format(longitude)}°"

fun City.toMarker(): CoreMarker = CoreMarker(
    title = name,
    coordinates = coordinates
)
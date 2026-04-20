package com.ldb.mobileualachallenge.feature.cities.presentation.mapper

import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData

fun City.toItemData(): CityListItemData = CityListItemData(
    id = id,
    title = "$name - ${countryPrefix.uppercase()}",
    subtitle = "${coordinates.latitude} - ${coordinates.longitude}",
    isFavorite = isFavorite
)
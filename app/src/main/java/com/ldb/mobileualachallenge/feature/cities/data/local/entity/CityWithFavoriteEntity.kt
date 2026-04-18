package com.ldb.mobileualachallenge.feature.cities.data.local.entity

import androidx.room.Embedded

data class CityWithFavoriteEntity(
    @Embedded val city: CityEntity,
    val isFavorite: Boolean
)
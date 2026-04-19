package com.ldb.mobileualachallenge.feature.cities.data.local.entity

import androidx.room.Embedded

data class CityFavoriteEmbeddedEntity(
    @Embedded val city: CityEntity,
    val isFavorite: Boolean
)
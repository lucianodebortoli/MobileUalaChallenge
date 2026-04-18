package com.ldb.mobileualachallenge.data.local.entity

import androidx.room.Embedded

data class CityWithFavoriteEntity(
    @Embedded val city: CityEntity,
    val isFavorite: Boolean
)
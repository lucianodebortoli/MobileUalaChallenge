package com.ldb.mobileualachallenge.feature.cities.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_cities")
data class FavoriteCityEntity(
    @PrimaryKey val id: CityEntityId,
)
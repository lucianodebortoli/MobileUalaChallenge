package com.ldb.mobileualachallenge.feature.cities.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_cities",
    indices = [Index(value = ["id"])]
)
data class FavoriteCityEntity(
    @PrimaryKey val id: CityEntityId,
)
package com.ldb.mobileualachallenge.feature.cities.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

typealias CityEntityId = Long

@Entity(
    tableName = "cities",
    indices = [Index(value = ["name"])]
)
data class CityEntity(
    @PrimaryKey val id: CityEntityId,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
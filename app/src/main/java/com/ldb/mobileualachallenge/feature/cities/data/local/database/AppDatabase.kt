package com.ldb.mobileualachallenge.feature.cities.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.FavoriteCityEntity

@Database(
    entities = [
        CityEntity::class,
        FavoriteCityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
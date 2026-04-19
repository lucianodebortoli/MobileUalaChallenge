package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.FavoriteCityEntity

@Dao
interface FavoriteCityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: FavoriteCityEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteCityEntity)

}
package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityWithFavoriteEntity

@Dao
interface CityWithFavoritesDao {

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
                "FROM cities c " +
                "LEFT JOIN favorite_cities f ON c.id = f.id " +
                "ORDER BY c.name"
    )
    fun getCitiesWithFavorites(): PagingSource<Int, CityWithFavoriteEntity>

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
                "FROM cities c " +
                "LEFT JOIN favorite_cities f ON c.id = f.id " +
                "WHERE c.name LIKE :query || '%' COLLATE NOCASE " +
                "ORDER BY name"
    )
    fun searchCitiesWithFavorites(query: String): PagingSource<Int, CityWithFavoriteEntity>

}
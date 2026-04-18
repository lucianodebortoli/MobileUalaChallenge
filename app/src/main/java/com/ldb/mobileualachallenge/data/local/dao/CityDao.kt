package com.ldb.mobileualachallenge.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ldb.mobileualachallenge.data.local.entity.CityEntity
import com.ldb.mobileualachallenge.data.local.entity.CityWithFavoriteEntity
import com.ldb.mobileualachallenge.data.local.entity.FavoriteCityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: FavoriteCityEntity)

    @Delete
    suspend fun removeFavorite(favorite: FavoriteCityEntity)

    @Query(
        value = "SELECT * " +
                "FROM cities " +
                "ORDER BY name"
    )
    fun getCities(): PagingSource<Int, CityEntity>

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
                "FROM cities c " +
                "LEFT JOIN favorite_cities f ON c.id = f.id " +
                "ORDER BY c.name"
    )
    fun getCitiesWithFavorites(): PagingSource<Int, CityWithFavoriteEntity>

    @Query(
        value = "SELECT * FROM cities " +
                "WHERE name LIKE :query || '%' COLLATE NOCASE " +
                "ORDER BY name"
    )
    fun searchCities(query: String): PagingSource<Int, CityEntity>

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
            "FROM cities c " +
            "LEFT JOIN favorite_cities f ON c.id = f.id " +
            "WHERE c.name LIKE :query || '%' " +
            "ORDER BY name"
    )
    fun searchCitiesWithFavorites(query: String): PagingSource<Int, CityWithFavoriteEntity>

}
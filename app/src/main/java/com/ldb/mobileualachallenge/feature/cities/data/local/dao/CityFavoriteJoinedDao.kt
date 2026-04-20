package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityFavoriteEmbeddedEntity
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

@Dao
interface CityFavoriteJoinedDao {

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
                "FROM cities c " +
                "LEFT JOIN favorite_cities f ON c.id = f.id " +
                "WHERE (NOT :filterFavorites OR f.id IS NOT NULL) " +
                "AND c.name LIKE :searchQuery || '%' COLLATE NOCASE " +
                "ORDER BY c.name ASC, c.country ASC"
    )
    fun getFilteredCities(
        searchQuery: String = "",
        filterFavorites: Boolean = false
    ): PagingSource<Int, CityFavoriteEmbeddedEntity>

    @Query(
        value = "SELECT c.*, (f.id IS NOT NULL) AS isFavorite " +
                "FROM cities c " +
                "LEFT JOIN favorite_cities f ON c.id = f.id " +
                "WHERE c.id = :cityId"
    )
    suspend fun getCity(cityId: CityId): CityFavoriteEmbeddedEntity?

}
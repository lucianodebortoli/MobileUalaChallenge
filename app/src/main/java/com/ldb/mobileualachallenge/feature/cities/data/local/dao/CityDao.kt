package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Query(
        value = "SELECT * " +
                "FROM cities " +
                "ORDER BY name"
    )
    fun getCities(): PagingSource<Int, CityEntity>

    @Query(
        value = "SELECT * FROM cities " +
                "WHERE name LIKE :query || '%' COLLATE NOCASE " +
                "ORDER BY name"
    )
    fun searchCities(query: String): PagingSource<Int, CityEntity>

}
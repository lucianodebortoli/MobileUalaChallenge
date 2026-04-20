package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Query(
        value = "SELECT * " +
                "FROM cities " +
                "ORDER BY name ASC, country ASC"
    )
    fun getCities(): PagingSource<Int, CityEntity>

    @Query(
        value = "SELECT * " +
                "FROM cities " +
                "WHERE id = :cityId"
    )
    fun getCity(cityId: CityId): PagingSource<Int, CityEntity>

}
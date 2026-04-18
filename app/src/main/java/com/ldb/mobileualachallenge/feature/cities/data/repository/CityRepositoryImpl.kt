package com.ldb.mobileualachallenge.feature.cities.data.repository

import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityWithFavoritesDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.FavoriteCityDao
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.GistApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.WikiApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.GistCityDto
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val favoriteCityDao: FavoriteCityDao,
    private val cityWithFavoritesDao: CityWithFavoritesDao,
    private val gistApi: GistApi,
    private val wikiApi: WikiApi
) : CityRepository {

}
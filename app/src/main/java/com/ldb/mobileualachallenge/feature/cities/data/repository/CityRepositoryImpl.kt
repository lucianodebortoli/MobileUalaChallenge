package com.ldb.mobileualachallenge.feature.cities.data.repository

import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.CityApi
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dao: CityDao,
    private val api: CityApi
) : CityRepository {

}
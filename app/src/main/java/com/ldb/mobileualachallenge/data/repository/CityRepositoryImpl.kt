package com.ldb.mobileualachallenge.data.repository

import com.ldb.mobileualachallenge.data.local.dao.CityDao
import com.ldb.mobileualachallenge.data.remote.api.CityApi
import com.ldb.mobileualachallenge.domain.repository.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val dao: CityDao,
    private val api: CityApi
) : CityRepository {

}
package com.ldb.mobileualachallenge.feature.cities.data.repository

import androidx.paging.PagingData
import com.ldb.mobileualachallenge.core.data.exception.EmptyDataException
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Fake City repository that does not depend on local or remote data sources.
 */
class FakeCityRepositoryImpl : CityRepository {

    private val cities = mutableListOf<City>()

    override suspend fun syncCities(force: Boolean): Result<Unit> {
        cities.clear()
        cities.addAll(FakeCityData.cities)
        return Result.success(Unit)
    }

    override suspend fun getCity(cityId: CityId): Result<City> {
        return cities.find { it.id == cityId }?.let { city ->
            Result.success(city)
        } ?: Result.failure(EmptyDataException())
    }

    override fun getCities(searchQuery: String, filterFavorites: Boolean): Flow<PagingData<City>> {
        return flow {
            val filteredList = cities.filter { city ->
                val startsWithQuery = city.name.startsWith(searchQuery, ignoreCase = false)
                val passesFavoriteFilter = if (filterFavorites) city.isFavorite else true
                startsWithQuery && passesFavoriteFilter
            }
            emit(PagingData.from(filteredList))
        }
    }

    override suspend fun getCityDetail(cityId: CityId): Result<CityDetail> {
        return getCity(cityId).getOrNull()?.let { city ->
            Result.success(
                CityDetail(
                    city = city,
                    additionalInfo = FakeCityData.cityAdditionalInfo
                )
            )
        } ?: Result.failure(EmptyDataException())
    }

    override suspend fun markFavoriteCity(cityId: CityId): Result<Unit> {
        return updateFavoriteCity(cityId = cityId, isFavorite = true)
    }

    override suspend fun unmarkFavoriteCity(cityId: CityId): Result<Unit> {
        return updateFavoriteCity(cityId = cityId, isFavorite = false)
    }

    private suspend fun updateFavoriteCity(cityId: CityId, isFavorite: Boolean): Result<Unit> {
        getCity(cityId).getOrNull() ?: return Result.failure(EmptyDataException())
        val updatedCities = cities.map { city ->
            if (city.id == cityId) city.copy(isFavorite = isFavorite) else city
        }
        cities.clear()
        cities.addAll(updatedCities)
        return Result.success(Unit)
    }

}
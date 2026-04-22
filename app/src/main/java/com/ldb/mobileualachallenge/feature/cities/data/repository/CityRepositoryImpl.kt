package com.ldb.mobileualachallenge.feature.cities.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.ldb.mobileualachallenge.core.data.exception.EmptyDataException
import com.ldb.mobileualachallenge.core.data.exception.NetworkException
import com.ldb.mobileualachallenge.core.data.extension.cleanHtml
import com.ldb.mobileualachallenge.core.framework.LOG_TAG
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityFavoriteJoinedDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.FavoriteCityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.FavoriteCityEntity
import com.ldb.mobileualachallenge.feature.cities.data.local.preferences.CitySyncPreferences
import com.ldb.mobileualachallenge.feature.cities.data.mapper.toCity
import com.ldb.mobileualachallenge.feature.cities.data.mapper.toLocalEntity
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.GistApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.WikiApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.WikiSearchDto
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.WikiSummaryDto
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import com.ldb.mobileualachallenge.main.data.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val cityDao: CityDao,
    private val favoriteCityDao: FavoriteCityDao,
    private val cityFavoriteJoinedDao: CityFavoriteJoinedDao,
    private val gistApi: GistApi,
    private val wikiApi: WikiApi,
    private val citySyncPreferences: CitySyncPreferences
) : CityRepository {

    override suspend fun syncCities(force: Boolean): Result<Unit> {
        if (!force && citySyncPreferences.isSyncRecent()) {
            return Result.success(Unit)
        }
        return withContext(Dispatchers.IO) {
            runCatching {
                val gistResponse = gistApi.getAllCities()
                if (!gistResponse.isSuccessful) throw NetworkException()
                val cities = gistResponse.body() ?: throw EmptyDataException()
                database.withTransaction {
                    cities.chunked(1000).forEach { chunk ->
                        cityDao.insertCities(
                            cities = chunk.map { gistDto -> gistDto.toLocalEntity() }
                        )
                    }
                }
                citySyncPreferences.setLastCitiesSyncTimestampMs(System.currentTimeMillis())
            }.onFailure { exception ->
                Log.e(LOG_TAG, "syncCities exception $exception")
            }
        }
    }

    override suspend fun getCity(cityId: CityId): Result<City> {
        return runCatching {
            val entity = cityFavoriteJoinedDao.getCity(cityId) ?: throw EmptyDataException()
            entity.toCity()
        }.onFailure { exception ->
            Log.e(LOG_TAG, "getCity exception $exception")
        }
    }

    override fun getCities(
        searchQuery: String,
        filterFavorites: Boolean
    ): Flow<PagingData<City>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                cityFavoriteJoinedDao.getFilteredCities(
                    searchQuery = searchQuery,
                    filterFavorites = filterFavorites
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toCity()
            }
        }
    }

    override suspend fun getCityDetail(cityId: CityId): Result<CityDetail> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val cityEntity = cityFavoriteJoinedDao.getCity(cityId) ?: throw EmptyDataException()
                val city = cityEntity.toCity()
                val baseQuery = city.name.replace(" ", "_")
                // 1. Try using city name with city suffix, to get more precise summary.
                val suffixSummaryResponse = wikiApi.getSummary(title = "${baseQuery}_City")
                if (suffixSummaryResponse.isSuccessful) {
                    val dto = suffixSummaryResponse.body()
                    return@runCatching CityDetail(
                        city = city,
                        additionalInfo = CityDetail.AdditionalInfo(
                            shortDescription = dto?.description,
                            longDescription = dto?.extract,
                            imageUrl = dto?.image?.url
                        )
                    )
                }
                // 2. Try to use just city name for the summary.
                val summaryResponse = wikiApi.getSummary(title = baseQuery)
                if (summaryResponse.isSuccessful) {
                    val dto = summaryResponse.body()
                    return@runCatching CityDetail(
                        city = city,
                        additionalInfo = CityDetail.AdditionalInfo(
                            shortDescription = dto?.description,
                            longDescription = dto?.extract,
                            imageUrl = dto?.image?.url
                        )
                    )
                }
                // 3. Finally, just use the result of the first search result.
                val searchResponse = wikiApi.searchPage(query = baseQuery)
                if (searchResponse.isSuccessful) {
                    val dto = searchResponse.body()?.pages?.first()
                    return@runCatching CityDetail(
                        city = city,
                        additionalInfo = CityDetail.AdditionalInfo(
                            shortDescription = dto?.description,
                            longDescription = dto?.excerptHtml?.cleanHtml(),
                            imageUrl = dto?.thumbnail?.url
                        )
                    )
                }
                throw EmptyDataException()
            }.onFailure { exception ->
                Log.e(LOG_TAG, "getCityDetail exception $exception")
            }
        }
    }

    override suspend fun markFavoriteCity(cityId: CityId): Result<Unit> {
        return runCatching {
            favoriteCityDao.addFavorite(FavoriteCityEntity(cityId))
        }.onFailure { exception ->
            Log.e(LOG_TAG, "addFavorite exception $exception")
        }
    }

    override suspend fun unmarkFavoriteCity(cityId: CityId): Result<Unit> {
        return runCatching {
            favoriteCityDao.removeFavorite(FavoriteCityEntity(cityId))
        }.onFailure { exception ->
            Log.e(LOG_TAG, "removeFavorite exception $exception")
        }
    }

}
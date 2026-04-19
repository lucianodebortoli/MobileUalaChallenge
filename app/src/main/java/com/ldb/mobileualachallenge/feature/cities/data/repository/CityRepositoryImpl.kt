package com.ldb.mobileualachallenge.feature.cities.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ldb.mobileualachallenge.core.data.exception.EmptyDataException
import com.ldb.mobileualachallenge.core.data.exception.NetworkException
import com.ldb.mobileualachallenge.core.data.extension.cleanHtml
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityFavoriteJoinedDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.FavoriteCityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.FavoriteCityEntity
import com.ldb.mobileualachallenge.feature.cities.data.mapper.toCity
import com.ldb.mobileualachallenge.feature.cities.data.mapper.toLocalEntity
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.GistApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.WikiApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.GistCityDto
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.DecodeSequenceMode
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeToSequence
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val favoriteCityDao: FavoriteCityDao,
    private val cityFavoriteJoinedDao: CityFavoriteJoinedDao,
    private val gistApi: GistApi,
    private val wikiApi: WikiApi,
    private val json: Json
) : CityRepository {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun syncCities(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val gistResponse = gistApi.streamAllCities()
                if (!gistResponse.isSuccessful) throw NetworkException()
                val gistBody = gistResponse.body() ?: throw EmptyDataException()
                gistBody.byteStream().use { inputStream ->
                    json.decodeToSequence<GistCityDto>(
                        stream = inputStream,
                        format = DecodeSequenceMode.ARRAY_WRAPPED
                    ).chunked(100).forEach { gistDtoChunk ->
                        cityDao.insertCities(
                            cities = gistDtoChunk.map { gistDto ->
                                gistDto.toLocalEntity()
                            }
                        )
                    }
                }
            }
        }
    }

    override fun getCities(
        searchQuery: String?,
        filterFavorites: Boolean
    ): Flow<PagingData<City>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                cityFavoriteJoinedDao.getCities(
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
                val cityNameQuery = city.name.replace(" ","_")
                val wikiSummaryDeferred = async { wikiApi.getSummary(title = cityNameQuery) }
                val wikiSearchDeferred = async { wikiApi.searchPage(query = cityNameQuery) }
                val wikiSummaryDto = wikiSummaryDeferred.await().takeIf { it.isSuccessful }?.body()
                val wikiSearchPageDto = wikiSearchDeferred.await().takeIf { it.isSuccessful }?.body()?.pages?.first()
                CityDetail(
                    city = city,
                    additionalInfo = CityDetail.AdditionalInfo(
                        shortDescription = wikiSummaryDto?.description ?: wikiSearchPageDto?.description,
                        longDescription = wikiSummaryDto?.extract ?: wikiSearchPageDto?.excerptHtml?.cleanHtml(),
                        imageUrl = wikiSummaryDto?.image?.url ?: wikiSummaryDto?.thumbnail?.url ?: wikiSearchPageDto?.thumbnail?.url
                    )
                )
            }
        }
    }

    override suspend fun markFavoriteCity(cityId: CityId): Result<Unit> {
        return runCatching {
            favoriteCityDao.addFavorite(FavoriteCityEntity(cityId))
        }
    }

    override suspend fun unmarkFavoriteCity(cityId: CityId): Result<Unit> {
        return runCatching {
            favoriteCityDao.removeFavorite(FavoriteCityEntity(cityId))
        }
    }

}
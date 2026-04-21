package com.ldb.mobileualachallenge.feature.cities.domain.repository

import androidx.paging.PagingData
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    /**
     * Syncs all cities.
     * Intended to be used for loading all cities during initialization.
     * @param force runs sync even if it was synced recently, otherwise skips sync.
     * @return a [Result] for handling sync errors.
     */
    suspend fun syncCities(force: Boolean): Result<Unit>

    /**
     * Gets the city based on its id.
     * @param cityId the [CityId] used for retrieving the [City].
     * @return a [Result] of [City].
     */
    suspend fun getCity(cityId: CityId): Result<City>

    /**
     * Observe paginated cities.
     * Intended to be used for listing cities.
     * @param searchQuery a [String] for filtering cities by prefix. Empty string matches all cities.
     * @param filterFavorites a [Boolean] to filter only favorite cities.
     * @return a [Flow] of [PagingData] of [City].
     */
    fun getCities(searchQuery: String, filterFavorites: Boolean): Flow<PagingData<City>>

    /**
     * Gets a city detail containing more information about the city.
     * @param cityId The [CityId] associated to the city.
     * @return a [Result] of [CityDetail] with the required additional information.
     */
    suspend fun getCityDetail(cityId: CityId): Result<CityDetail>

    /**
     * Marks a city as favorite.
     * This can be undone by using [unmarkFavoriteCity].
     * @return a [Result] for handling erros.
     */
    suspend fun markFavoriteCity(cityId: CityId): Result<Unit>

    /**
     * Unmarks a city as favorite.
     * Intended to be used when a city is already marked as favorite using [markFavoriteCity].
     * @return a [Result] for handling errors.
     */
    suspend fun unmarkFavoriteCity(cityId: CityId): Result<Unit>

}
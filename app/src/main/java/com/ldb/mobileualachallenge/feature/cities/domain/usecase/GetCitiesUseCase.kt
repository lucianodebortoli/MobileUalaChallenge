package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import androidx.paging.PagingData
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCitiesUseCase @Inject constructor(
    private val repository: CityRepository
)  {

    operator fun invoke(
        searchQuery: String = "",
        filterFavorites: Boolean
    ): Flow<PagingData<City>> {
        return repository.getCities(
            searchQuery = searchQuery,
            filterFavorites = filterFavorites
        )
    }

}
package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UpdateCityUseCase @Inject constructor(
    private val repository: CityRepository
)  {

    suspend operator fun invoke(cityId: CityId, isFavorite: Boolean) {
        when (isFavorite) {
            true -> repository.markFavoriteCity(cityId)
            false -> repository.unmarkFavoriteCity(cityId)
        }
    }

}
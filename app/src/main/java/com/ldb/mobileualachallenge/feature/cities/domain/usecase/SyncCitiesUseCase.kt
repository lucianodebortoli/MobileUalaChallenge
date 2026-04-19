package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SyncCitiesUseCase @Inject constructor(
    private val repository: CityRepository
)  {

    suspend operator fun invoke(): Result<Unit> {
        return repository.syncCities()
    }

}
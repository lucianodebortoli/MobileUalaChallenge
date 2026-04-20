package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCityUseCase @Inject constructor(
    private val repository: CityRepository
)  {

    suspend operator fun invoke(cityId: CityId): Result<City> {
        return repository.getCity(
            cityId = cityId
        )
    }

}
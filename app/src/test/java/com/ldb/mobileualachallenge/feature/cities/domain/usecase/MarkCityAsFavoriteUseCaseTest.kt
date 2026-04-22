package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import android.annotation.SuppressLint
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityData
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityRepositoryImpl
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Intended to test marking a city as favorite and unmarking it as favorite.
 */
class MarkCityAsFavoriteUseCaseTest {

    private lateinit var repository: CityRepository
    private lateinit var markCityAsFavoriteUseCase: MarkCityAsFavoriteUseCase

    @Before
    fun setup() {
        repository = FakeCityRepositoryImpl()
        markCityAsFavoriteUseCase = MarkCityAsFavoriteUseCase(repository)
        runBlocking {
            repository.syncCities(force = true)
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun markingFavoriteAnExistingCityId_markedCorrectly() {
        runBlocking {
            val existingCityId = FakeCityData.cities.first().id
            val targetIsFavoriteValue = true

            // 1. Check the use case handles an existing city id (with success):
            val result = markCityAsFavoriteUseCase(
                cityId = existingCityId,
                isFavorite = targetIsFavoriteValue
            )
            assertThat(result.getOrNull() != null)

            // 2. Check the use case updated the repository data correctly:
            val isFavorite = repository.getCity(existingCityId).getOrNull()?.isFavorite
            assertThat(isFavorite).isEqualTo(targetIsFavoriteValue)

        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun markingNotFavoriteAnExistingCityId_unmarkedCorrectly() {
        runBlocking {
            val existingCityId = FakeCityData.cities.last().id
            val targetIsFavoriteValue = false

            // 1. Check the use case handles an existing city id (with success):
            val result = markCityAsFavoriteUseCase(
                cityId = existingCityId,
                isFavorite = targetIsFavoriteValue
            )
            assertThat(result.getOrNull()).isNotNull()

            // 2. Check the use case updated the repository data correctly:
            val isFavorite = repository.getCity(existingCityId).getOrNull()?.isFavorite
            assertThat(isFavorite).isEqualTo(targetIsFavoriteValue)

        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun markingNotExistingCityId_resultsFailure() {
        runBlocking {
            val nonExistingCityId = -1L
            val targetIsFavoriteValue = true
            val result = markCityAsFavoriteUseCase(
                cityId = nonExistingCityId,
                isFavorite = targetIsFavoriteValue
            )
            assertThat(result.getOrNull()).isNull()
        }
    }

}
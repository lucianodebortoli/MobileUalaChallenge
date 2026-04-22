package com.ldb.mobileualachallenge.feature.cities.domain.usecase

import android.annotation.SuppressLint
import androidx.paging.testing.asSnapshot
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityData
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityRepositoryImpl
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * GetCitiesUseCase is mainly functions as a passthrough use case.
 */
class GetCitiesUseCaseTest {

    private lateinit var repository: CityRepository
    private lateinit var getCitiesUseCase: GetCitiesUseCase

    @Before
    fun setup() {
        repository = FakeCityRepositoryImpl()
        getCitiesUseCase = GetCitiesUseCase(repository)
        runBlocking {
            repository.syncCities(force = true)
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun gettingCitiesWithEmptyTextAndNoFilter_returnsAllCities() {
        runBlocking {
            val cities: List<City> = getCitiesUseCase(
                searchQuery = "",
                filterFavorites = false
            ).asSnapshot()
            assertThat(cities).isEqualTo(FakeCityData.cities)
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun gettingCitiesStartingWithA_returnsAllCitiesStartingWithA() {
        runBlocking {
            val searchQuery = "A"
            val citiesStartingWithA = FakeCityData.cities.filter { it.name.startsWith(searchQuery) }
            val citiesResult: List<City> = getCitiesUseCase(
                searchQuery = searchQuery,
                filterFavorites = false
            ).asSnapshot()
            assertThat(citiesResult).isEqualTo(citiesStartingWithA)
        }
    }

    @SuppressLint("CheckResult")
    @Test
    fun gettingCitiesStartingWithS_returnsAllCitiesStartingWithS() {
        runBlocking {
            val searchQuery = "S"
            val citiesStartingWithA = FakeCityData.cities.filter { it.name.startsWith(searchQuery) }
            val citiesResult: List<City> = getCitiesUseCase(
                searchQuery = searchQuery,
                filterFavorites = false
            ).asSnapshot()
            assertThat(citiesResult).isEqualTo(citiesStartingWithA)
        }
    }

}
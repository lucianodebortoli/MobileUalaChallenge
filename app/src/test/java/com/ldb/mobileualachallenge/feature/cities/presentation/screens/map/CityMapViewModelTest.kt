package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.core.test.MainDispatcherRule
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityData
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Note:
 * Since this viewmodel uses SavedStateHandle, and we use SavedStateHandle.toRoute(),
 * we require robolectric to run this as unit test.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [34])
class CityMapViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cityId: CityId = FakeCityData.cities.first().id
    private val city = FakeCityData.cities.first()

    @Test
    fun successfulLoad_toReadyState() = runTest {
        val getCityUseCase = mockk<GetCityUseCase>()
        coEvery { getCityUseCase(cityId) } returns Result.success(city)
        val viewModel = buildViewModel(getCityUseCase = getCityUseCase)
        viewModel.mapState.test {
            assertThat(awaitItem()).isEqualTo(CityMapState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityMapState.Ready)
        }
    }

    @Test
    fun failedLoad_toErrorState() = runTest {
        val getCityUseCase = mockk<GetCityUseCase>()
        coEvery { getCityUseCase(cityId) } returns Result.failure(RuntimeException("exception"))
        val viewModel = buildViewModel(getCityUseCase = getCityUseCase)
        viewModel.mapState.test {
            assertThat(awaitItem()).isEqualTo(CityMapState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityMapState.Error)
        }
    }

    private fun buildViewModel(
        getCityUseCase: GetCityUseCase = mockk()
    ): CityMapViewModel = CityMapViewModel(
        savedStateHandle = SavedStateHandle(mapOf("cityId" to cityId)),
        getCityUseCase = getCityUseCase
    )

}
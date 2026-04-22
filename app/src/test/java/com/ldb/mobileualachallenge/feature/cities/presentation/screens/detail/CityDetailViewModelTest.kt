package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.core.test.MainDispatcherRule
import com.ldb.mobileualachallenge.feature.cities.data.repository.FakeCityData
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityDetailUseCase
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.MarkCityAsFavoriteUseCase
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
class CityDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val cityId: CityId = FakeCityData.cities.first().id

    private val cityDetail: CityDetail = CityDetail(
        city = FakeCityData.cities.first(),
        additionalInfo = FakeCityData.cityAdditionalInfo
    )

    @Test
    fun successfulLoad_toReadyState() = runTest {
        val getDetailUseCase = mockk<GetCityDetailUseCase>()
        coEvery { getDetailUseCase(cityId) } returns Result.success(cityDetail)
        val viewModel = buildViewModel(getCityDetailUseCase = getDetailUseCase)
        viewModel.detailState.test {
            assertThat(awaitItem()).isEqualTo(CityDetailState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityDetailState.Ready)
        }
    }

    @Test
    fun failureLoad_toErrorState() = runTest {
        val getDetailUseCase = mockk<GetCityDetailUseCase>()
        coEvery { getDetailUseCase(cityId) } returns Result.failure(RuntimeException("exception"))
        val viewModel = buildViewModel(getCityDetailUseCase = getDetailUseCase)
        viewModel.detailState.test {
            assertThat(awaitItem()).isEqualTo(CityDetailState.Loading)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityDetailState.Error)
        }
    }

    @Test
    fun cityName_reflectsLoadedDetail() = runTest {
        val getDetailUseCase = mockk<GetCityDetailUseCase>()
        coEvery { getDetailUseCase(cityId) } returns Result.success(cityDetail)
        val viewModel = buildViewModel(getCityDetailUseCase = getDetailUseCase)
        viewModel.cityName.test {
            assertThat(awaitItem()).isNull()
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(cityDetail.city.name)
        }
    }

    private fun buildViewModel(
        getCityDetailUseCase: GetCityDetailUseCase = mockk(),
        markCityAsFavoriteUseCase: MarkCityAsFavoriteUseCase = mockk()
    ): CityDetailViewModel = CityDetailViewModel(
        savedStateHandle = SavedStateHandle(mapOf("cityId" to cityId)),
        getCityDetailUseCase = getCityDetailUseCase,
        markCityAsFavoriteUseCase = markCityAsFavoriteUseCase
    )

}
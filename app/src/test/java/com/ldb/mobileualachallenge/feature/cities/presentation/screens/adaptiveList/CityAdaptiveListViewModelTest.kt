package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.core.test.MainDispatcherRule
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCitiesUseCase
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityUseCase
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.MarkCityAsFavoriteUseCase
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.SyncCitiesUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CityAdaptiveListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun successfulSync_syncStateListReady() = runTest {
        val syncCitiesUseCase = mockk<SyncCitiesUseCase>()
        coEvery { syncCitiesUseCase(force = false) } returns Result.success(Unit)
        val viewModel = buildViewModel(syncCitiesUseCase = syncCitiesUseCase)
        viewModel.syncState.test {
            assertThat(awaitItem()).isEqualTo(CityListSyncState.Syncing)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityListSyncState.ListReady)
        }
    }

    @Test
    fun failedSync_syncStateError() = runTest {
        val syncCitiesUseCase = mockk<SyncCitiesUseCase>()
        coEvery { syncCitiesUseCase(force = false) } returns Result.failure(RuntimeException("exception"))
        val viewModel = buildViewModel(syncCitiesUseCase = syncCitiesUseCase)
        viewModel.syncState.test {
            assertThat(awaitItem()).isEqualTo(CityListSyncState.Syncing)
            advanceUntilIdle()
            assertThat(awaitItem()).isEqualTo(CityListSyncState.Error)
        }
    }

    @Test
    fun filterButtonClicked_togglesFavoritesOnly() = runTest {
        val viewModel = buildViewModel()
        advanceUntilIdle()
        assertThat(viewModel.favoritesOnly.value).isFalse()
        viewModel.onEvent(CityAdaptiveListEvent.OnFilterButtonClicked)
        assertThat(viewModel.favoritesOnly.value).isTrue()
        viewModel.onEvent(CityAdaptiveListEvent.OnFilterButtonClicked)
        assertThat(viewModel.favoritesOnly.value).isFalse()
    }

    @Test
    fun searchQueryChanged_updatesQueryState() = runTest {
        val viewModel = buildViewModel()
        advanceUntilIdle()
        viewModel.onEvent(CityAdaptiveListEvent.OnSearchQueryChanged("Syd"))
        assertThat(viewModel.searchQuery.value).isEqualTo("Syd")
        viewModel.onEvent(CityAdaptiveListEvent.OnSearchQueryChanged(""))
        assertThat(viewModel.searchQuery.value).isEqualTo("")
    }

    private fun buildViewModel(
        syncCitiesUseCase: SyncCitiesUseCase = mockk<SyncCitiesUseCase>(relaxed = true).apply {
            coEvery {
                this@apply.invoke(
                    force = any()
                )
            } returns Result.success(Unit)
        },
        getCitiesUseCase: GetCitiesUseCase = mockk<GetCitiesUseCase>().apply {
            every {
                this@apply.invoke(
                    searchQuery = any(),
                    filterFavorites = any()
                )
            } returns flowOf(PagingData.empty())
        },
        getCityUseCase: GetCityUseCase = mockk(),
        markCityAsFavoriteUseCase: MarkCityAsFavoriteUseCase = mockk()
    ): CityAdaptiveListViewModel = CityAdaptiveListViewModel(
        syncCitiesUseCase = syncCitiesUseCase,
        getCitiesUseCase = getCitiesUseCase,
        getCityUseCase = getCityUseCase,
        markCityAsFavoriteUseCase = markCityAsFavoriteUseCase
    )

}
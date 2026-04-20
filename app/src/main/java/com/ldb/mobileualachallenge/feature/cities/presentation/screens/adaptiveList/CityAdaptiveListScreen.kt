package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityErrorSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CitySyncSection

@Composable
fun CityAdaptiveListScreen(navController: NavController) {
    val viewModel: CityAdaptiveListViewModel = hiltViewModel()
    val syncState by viewModel.syncState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val favoritesOnly by viewModel.favoritesOnly.collectAsStateWithLifecycle()
    val selectedItemId by viewModel.selectedCityId.collectAsStateWithLifecycle()
    val items = viewModel.citiesPager.collectAsLazyPagingItems()
    AdaptiveCityListContent(
        syncState = syncState,
        searchQuery = searchQuery,
        favoritesOnly = favoritesOnly,
        selectedItemId = selectedItemId,
        items = items,
        onDetailsClicked = { cityId ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityDetailsClicked(cityId))
        },
        onFavoriteClicked = { cityId, isFavorite ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityFavoriteClicked(cityId, isFavorite))
        },
        onSearchQueryChanged = { query ->
            viewModel.onEvent(CityAdaptiveListEvent.OnSearchQueryChanged(query))
        },
        onFilterButtonClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnFilterButtonClicked)
        },
        onSyncRetryClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnSyncRetryClicked)
        }
    )
}

@Composable
private fun AdaptiveCityListContent(
    syncState: SyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit
) {
    val configuration = LocalConfiguration.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        val rootModifier = Modifier
            .padding(paddingValues)
            .consumeWindowInsets(WindowInsets.navigationBars)
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeCityListContent(
                modifier = rootModifier,
                syncState = syncState,
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
                selectedItemId = selectedItemId,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked,
                onSyncRetryClicked = onSyncRetryClicked
            )
            else -> PortraitCityListContent(
                modifier = rootModifier,
                syncState = syncState,
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
                selectedItemId = selectedItemId,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked,
                onSyncRetryClicked = onSyncRetryClicked
            )
        }
    }
}

@Composable
private fun PortraitCityListContent(
    modifier: Modifier,
    syncState: SyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        when (syncState) {
            SyncState.Syncing -> CitySyncSection(
                modifier = Modifier.fillMaxSize()
            )
            SyncState.Error -> CityErrorSection(
                modifier = Modifier.fillMaxSize(),
                onRetryClicked = onSyncRetryClicked
            )
            SyncState.ListReady -> CityListSection(
                searchQuery = searchQuery,
                onlyFavorites = favoritesOnly,
                selectedItemId = selectedItemId,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked
            )
        }
    }
}

@Composable
private fun LandscapeCityListContent(
    modifier: Modifier,
    syncState: SyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Landscape Liss Section")
        Text("Landscape Map Section")
    }
}
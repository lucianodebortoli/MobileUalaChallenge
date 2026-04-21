package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsStartWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.framework.AdaptiveOrientation
import com.ldb.mobileualachallenge.core.framework.getAdaptiveOrientation
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityErrorSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CitySyncSection

@Composable
fun CityAdaptiveListScreen(
    viewModel: CityAdaptiveListViewModel = hiltViewModel(),
    onScreenResult: (CityAdaptiveListResult) -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val orientation = configuration.getAdaptiveOrientation()
    val snackbarHostState = remember { SnackbarHostState() }
    val syncState by viewModel.syncState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val favoritesOnly by viewModel.favoritesOnly.collectAsStateWithLifecycle()
    val selectedItem by viewModel.selectedCity.collectAsStateWithLifecycle()
    val items = viewModel.citiesPager.collectAsLazyPagingItems()
    LaunchedEffect(Unit) {
        viewModel.actions.collect { action ->
            when (action) {
                is CityAdaptiveListAction.NavigateToDetail -> {
                    onScreenResult(CityAdaptiveListResult.NavigateToCityDetail(cityId = action.cityId))
                }
                is CityAdaptiveListAction.NavigateToMap -> {
                    if (orientation == AdaptiveOrientation.Portrait) {
                        onScreenResult(CityAdaptiveListResult.NavigateToCityMap(cityId = action.city.id))
                    }
                }
                is CityAdaptiveListAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = context.getString(action.stringRes)
                    )
                }
            }
        }
    }
    AdaptiveCityListLayout(
        orientation = orientation,
        syncState = syncState,
        searchQuery = searchQuery,
        favoritesOnly = favoritesOnly,
        selectedItem = selectedItem,
        items = items,
        onDetailsClicked = { cityId ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityDetailsClicked(cityId))
        },
        onFavoriteClicked = { cityId, isFavorite ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityFavoriteChanged(cityId, isFavorite))
        },
        onSearchQueryChanged = { query ->
            viewModel.onEvent(CityAdaptiveListEvent.OnSearchQueryChanged(query))
        },
        onFilterButtonClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnFilterButtonClicked)
        },
        onSyncRetryClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnSyncRetryClicked)
        },
        onClickCity = { cityId ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityClicked(cityId))
        }
    )
}

@Composable
private fun AdaptiveCityListLayout(
    orientation: AdaptiveOrientation,
    syncState: SyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItem: City?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit,
    onClickCity: (CityId) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = stringResource(R.string.feature_cities_list_title),
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        val rootModifier = Modifier
            .padding(paddingValues)
            .consumeWindowInsets(WindowInsets.safeDrawing)
        when (orientation) {
            AdaptiveOrientation.Portrait -> PortraitCityListContent(
                modifier = rootModifier,
                syncState = syncState,
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
                selectedItem = selectedItem,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked,
                onSyncRetryClicked = onSyncRetryClicked,
                onClickCity = onClickCity
            )
            AdaptiveOrientation.Landscape -> LandscapeCityListContent(
                modifier = rootModifier,
                syncState = syncState,
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
                selectedItem = selectedItem,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked,
                onSyncRetryClicked = onSyncRetryClicked,
                onClickCity = onClickCity
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
    selectedItem: City?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit,
    onClickCity: (CityId) -> Unit
) {
    Box(modifier = modifier) {
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
                selectedItemId = selectedItem?.id,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked,
                onClickItem = onClickCity
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
    selectedItem: City?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit,
    onClickCity: (CityId) -> Unit
) {
    Box(modifier = modifier) {
        when (syncState) {
            SyncState.Syncing -> CitySyncSection(
                modifier = Modifier.fillMaxSize()
            )
            SyncState.Error -> CityErrorSection(
                modifier = Modifier.fillMaxSize(),
                onRetryClicked = onSyncRetryClicked
            )
            SyncState.ListReady -> Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CityListSection(
                    modifier = Modifier.weight(0.5f),
                    searchQuery = searchQuery,
                    onlyFavorites = favoritesOnly,
                    selectedItemId = selectedItem?.id,
                    items = items,
                    onDetailsClicked = onDetailsClicked,
                    onFavoriteClicked = onFavoriteClicked,
                    onSearchQueryChanged = onSearchQueryChanged,
                    onFilterButtonClicked = onFilterButtonClicked,
                    onClickItem = onClickCity
                )
                Text(
                    modifier = Modifier.weight(0.5f),
                    text = "Landscape Map Section"
                )
            }
        }
    }
}
package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.ldb.mobileualachallenge.core.domain.model.CoreMarker
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListErrorSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityMapSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListSyncSection

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
    val selectedItemId by viewModel.selectedCityId.collectAsStateWithLifecycle()
    val selectedMarker by viewModel.selectedCityMarker.collectAsStateWithLifecycle()
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
        selectedItemId = selectedItemId,
        selectedMarker = selectedMarker,
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
        onMenuReloadClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnMenuReloadClicked)
        },
        onClickCity = { cityId ->
            viewModel.onEvent(CityAdaptiveListEvent.OnCityClicked(cityId))
        }
    )
}

@Composable
private fun AdaptiveCityListLayout(
    orientation: AdaptiveOrientation,
    syncState: CityListSyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    selectedMarker: CoreMarker?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit,
    onSyncRetryClicked: () -> Unit,
    onMenuReloadClicked: () -> Unit,
    onClickCity: (CityId) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = stringResource(R.string.feature_cities_list_title),
                actions = {
                    if (syncState == CityListSyncState.ListReady) {
                        IconButton(onClick = onMenuReloadClicked) {
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "Reload Button",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
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
                selectedItemId = selectedItemId,
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
                selectedItemId = selectedItemId,
                selectedMarker = selectedMarker,
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
    syncState: CityListSyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
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
            CityListSyncState.Syncing -> CityListSyncSection(
                modifier = Modifier.fillMaxSize()
            )
            CityListSyncState.Error -> CityListErrorSection(
                modifier = Modifier.fillMaxSize(),
                onRetryClicked = onSyncRetryClicked
            )
            CityListSyncState.ListReady -> CityListSection(
                searchQuery = searchQuery,
                onlyFavorites = favoritesOnly,
                selectedItemId = selectedItemId,
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
    syncState: CityListSyncState,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    selectedMarker: CoreMarker?,
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
            CityListSyncState.Syncing -> CityListSyncSection(
                modifier = Modifier.fillMaxSize()
            )
            CityListSyncState.Error -> CityListErrorSection(
                modifier = Modifier.fillMaxSize(),
                onRetryClicked = onSyncRetryClicked
            )
            CityListSyncState.ListReady -> Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CityListSection(
                    modifier = Modifier.weight(0.45f),
                    searchQuery = searchQuery,
                    onlyFavorites = favoritesOnly,
                    selectedItemId = selectedItemId,
                    items = items,
                    onDetailsClicked = onDetailsClicked,
                    onFavoriteClicked = onFavoriteClicked,
                    onSearchQueryChanged = onSearchQueryChanged,
                    onFilterButtonClicked = onFilterButtonClicked,
                    onClickItem = onClickCity
                )
                CityMapSection(
                    modifier = Modifier
                        .weight(0.55f)
                        .fillMaxHeight()
                        .padding(Dimensions.Spacing.medium)
                        .clip(shape = RoundedCornerShape(Dimensions.CornerRadius.medium))
                        .shadow(
                            elevation = Dimensions.Elevation.medium,
                            shape = RoundedCornerShape(Dimensions.CornerRadius.medium)
                        ),
                    marker = selectedMarker
                )
            }
        }
    }
}
package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityListSection

@Composable
fun CityAdaptiveListScreen(navController: NavController) {
    val viewModel: CityAdaptiveListViewModel = hiltViewModel()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val favoritesOnly by viewModel.favoritesOnly.collectAsStateWithLifecycle()
    val selectedItemId by viewModel.selectedCityId.collectAsStateWithLifecycle()
    val items = viewModel.citiesPager.collectAsLazyPagingItems()
    AdaptiveCityListContent(
        searchQuery = searchQuery,
        favoritesOnly = favoritesOnly,
        selectedItemId = selectedItemId,
        items = items,
        onDetailsClicked = { cityId ->
            viewModel.onEvent(CityAdaptiveListEvent.OnDetailsClicked(cityId))
        },
        onFavoriteClicked = { cityId, isFavorite ->
            viewModel.onEvent(CityAdaptiveListEvent.OnFavoriteClicked(cityId, isFavorite))
        },
        onSearchQueryChanged = { query ->
            viewModel.onEvent(CityAdaptiveListEvent.OnSearchQueryChanged(query))
        },
        onFilterButtonClicked = {
            viewModel.onEvent(CityAdaptiveListEvent.OnFilterButtonClicked)
        },
    )
}

@Composable
private fun AdaptiveCityListContent(
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit
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
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
                selectedItemId = selectedItemId,
                items = items,
                onDetailsClicked = onDetailsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onSearchQueryChanged = onSearchQueryChanged,
                onFilterButtonClicked = onFilterButtonClicked
            )
            else -> PortraitCityListContent(
                modifier = rootModifier,
                searchQuery = searchQuery,
                favoritesOnly = favoritesOnly,
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
private fun PortraitCityListContent(
    modifier: Modifier,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        CityListSection(
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

@Composable
private fun LandscapeCityListContent(
    modifier: Modifier,
    searchQuery: String,
    favoritesOnly: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text("Landscape Screen")
    }
}
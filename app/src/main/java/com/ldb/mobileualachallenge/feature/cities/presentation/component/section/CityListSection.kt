package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.empty.CoreEmptyView
import com.ldb.mobileualachallenge.core.presentation.component.field.CoreSearchFavoritesField
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.component.progress.CoreProgressBar
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItem
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.cityPreviewItems
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListSection(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onlyFavorites: Boolean,
    selectedItemId: CityId?,
    items: LazyPagingItems<CityListItemData>,
    onClickItem: (CityId) -> Unit,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit
) {
    val isEmpty = items.loadState.refresh is LoadState.NotLoading && items.itemCount == 0
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)
    ) {
        CoreSearchFavoritesField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = Dimensions.Spacing.medium,
                    start = Dimensions.Spacing.medium,
                    end = Dimensions.Spacing.medium
                ),
            value = searchQuery,
            onlyFavorites = onlyFavorites,
            onValueChange = onSearchQueryChanged,
            onFavoriteClicked = onFilterButtonClicked,
        )
        Box(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium),
                contentPadding = PaddingValues(Dimensions.Spacing.medium)
            ) {
                items(
                    count = items.itemCount,
                    key = items.itemKey { it.id },
                ) { index ->
                    items[index]?.let { data ->
                        CityListItem(
                            modifier = Modifier.animateItem(),
                            data = data,
                            isSelected = data.id == selectedItemId,
                            onDetailsClicked = {
                                onDetailsClicked(data.id)
                            },
                            onFavoriteClicked = { isFavorite ->
                                onFavoriteClicked(data.id, isFavorite)
                            },
                            onClick = {
                                onClickItem(data.id)
                            }
                        )
                    }
                }
            }
            if (isEmpty) {
                CoreEmptyView(
                    title = stringResource(R.string.feature_cities_list_empty)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    CorePreview(padding = 0.dp) {
        val emptyCities: List<CityListItemData> = emptyList()
        val flow = flowOf(PagingData.from(emptyCities))
        val items = flow.collectAsLazyPagingItems()
        CityListSection(
            searchQuery = "",
            items = items,
            onlyFavorites = false,
            selectedItemId = 0,
            onFavoriteClicked = { _, _ -> },
            onDetailsClicked = {},
            onSearchQueryChanged = {},
            onFilterButtonClicked = {},
            onClickItem = {}
        )
    }
}

@Preview
@Composable
private fun EmptyPreview() {
    CorePreview {
        val flow = flowOf(
            value = PagingData.empty<CityListItemData>(
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(endOfPaginationReached = true),
                    prepend = LoadState.NotLoading(endOfPaginationReached = true),
                    append = LoadState.NotLoading(endOfPaginationReached = true)
                )
            )
        )
        val items = flow.collectAsLazyPagingItems()
        CityListSection(
            searchQuery = "",
            items = items,
            onlyFavorites = false,
            selectedItemId = 0,
            onFavoriteClicked = { _, _ -> },
            onDetailsClicked = {},
            onSearchQueryChanged = {},
            onFilterButtonClicked = {},
            onClickItem = {}
        )
    }
}

@Preview
@Composable
private fun ListPreview() {
    CorePreview {
        val flow = flowOf(PagingData.from(cityPreviewItems))
        val items = flow.collectAsLazyPagingItems()
        CityListSection(
            searchQuery = "",
            items = items,
            onlyFavorites = false,
            selectedItemId = 0,
            onFavoriteClicked = { _, _ -> },
            onDetailsClicked = {},
            onSearchQueryChanged = {},
            onFilterButtonClicked = {},
            onClickItem = {}
        )
    }
}
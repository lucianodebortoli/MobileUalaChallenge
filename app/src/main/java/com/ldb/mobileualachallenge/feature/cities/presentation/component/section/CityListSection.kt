package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.field.CoreSearchFavoritesField
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItem
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.cityPreviewItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListSection(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onlyFavorites: Boolean,
    items: List<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onFilterButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)
    ) {
        CoreSearchFavoritesField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            onlyFavorites = onlyFavorites,
            onValueChange = onSearchQueryChanged,
            onFavoriteClicked = onFilterButtonClicked,
        )
        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)
        ) {
            items(
                items = items,
                key = { item -> item.id }
            ) { item ->
                CityListItem(
                    data = item,
                    onDetailsClicked = {
                        onDetailsClicked(item.id)
                    },
                    onFavoriteClicked = { isFavorite ->
                        onFavoriteClicked(item.id, isFavorite)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CityListSection(
            searchQuery = "",
            items = cityPreviewItems,
            onlyFavorites = false,
            onFavoriteClicked = { _, _ -> },
            onDetailsClicked = {},
            onSearchQueryChanged = {},
            onFilterButtonClicked = {}
        )
    }
}
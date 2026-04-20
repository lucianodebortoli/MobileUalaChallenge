package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions.Spacing
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItem
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.cityPreviewItems

@Composable
fun CityListSection(
    modifier: Modifier = Modifier,
    items: List<CityListItemData>,
    onDetailsClicked: (CityId) -> Unit,
    onFavoriteClicked: (CityId, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Spacing.small)
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

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CityListSection(
            items = cityPreviewItems,
            onFavoriteClicked = { _, _ -> },
            onDetailsClicked = {}
        )
    }
}
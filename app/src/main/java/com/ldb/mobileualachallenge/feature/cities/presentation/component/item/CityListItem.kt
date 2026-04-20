package com.ldb.mobileualachallenge.feature.cities.presentation.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.domain.extension.toggled
import com.ldb.mobileualachallenge.core.presentation.component.button.CoreButton
import com.ldb.mobileualachallenge.core.presentation.component.button.CoreButtonDefaults
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.component.surface.CoreOutlinedSurface
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions
import com.ldb.mobileualachallenge.core.presentation.theme.GoldMedium
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

@Stable
data class CityListItemData(
    val id: CityId,
    val title: String,
    val subtitle: String,
    val isFavorite: Boolean,
)

@Composable
fun CityListItem(
    modifier: Modifier = Modifier,
    data: CityListItemData,
    isSelected: Boolean,
    onDetailsClicked: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    CoreOutlinedSurface(
        modifier = modifier.clickable {
            onClick()
        },
        borderColor = when (isSelected) {
            true -> MaterialTheme.colorScheme.primary
            false -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        },
        surfaceColor = when (isSelected) {
            true -> MaterialTheme.colorScheme.onPrimaryContainer
            false -> MaterialTheme.colorScheme.surface
        },
        contentColor = when (isSelected) {
            true -> MaterialTheme.colorScheme.primaryContainer
            false -> MaterialTheme.colorScheme.onSurface
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Spacing.medium),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.extraSmall)) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = data.subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
                DetailsButton(
                    isSelected = isSelected,
                    onClicked = onDetailsClicked
                )
            }
            FavoriteStarButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                isFavorite = data.isFavorite,
                onClicked = onFavoriteClicked
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(Dimensions.IconSize.medium),
                imageVector = Icons.Default.LocationOn,
                tint = MaterialTheme.colorScheme.primaryContainer,
                contentDescription = "Location Icon"
            )
        }
    }
}

@Composable
private fun DetailsButton(
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    CoreButton(
        title = stringResource(R.string.feature_cities_action_show_details),
        colors = CoreButtonDefaults.colors().copy(
            containerColor = when (isSelected) {
                true -> MaterialTheme.colorScheme.primaryContainer
                false -> MaterialTheme.colorScheme.onPrimaryContainer
            },
            contentColor = when (isSelected) {
                true -> MaterialTheme.colorScheme.onPrimaryContainer
                false -> MaterialTheme.colorScheme.primaryContainer
            }
        ),
        onClick = onClicked
    )
}

@Composable
private fun FavoriteStarButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onClicked: (Boolean) -> Unit
) {
    Button(
        modifier = modifier.size(Dimensions.IconSize.medium),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            onClicked(isFavorite.toggled())
        }
    ) {
        Icon(
            imageVector = when (isFavorite) {
                true -> Icons.Filled.Star
                false -> Icons.Outlined.Star
            },
            contentDescription = "Favorite Button",
            tint = when (isFavorite) {
                true -> GoldMedium
                false -> MaterialTheme.colorScheme.outlineVariant
            }
        )
    }
}

val cityPreviewItems: List<CityListItemData> = listOf(
    CityListItemData(
        id = 0,
        title = "Buenos Aires - AR",
        subtitle = "Coordinates",
        isFavorite = true,
    ),
    CityListItemData(
        id = 1,
        title = "Paris - FR",
        subtitle = "Coordinates",
        isFavorite = false,
    ),
    CityListItemData(
        id = 2,
        title = "New York - US",
        subtitle = "Coordinates",
        isFavorite = false,
    )
)

@Preview
@Composable
private fun Preview() {
    CorePreview {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)) {
            cityPreviewItems.forEachIndexed { index, data ->
                CityListItem(
                    data = data,
                    isSelected = index % 2 == 1,
                    onDetailsClicked = {},
                    onFavoriteClicked = {},
                    onClick = {}
                )
            }
        }
    }
}
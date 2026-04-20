package com.ldb.mobileualachallenge.feature.cities.presentation.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.domain.extension.toggled
import com.ldb.mobileualachallenge.core.presentation.component.button.CoreButton
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
    val isSelected: Boolean,
)

@Composable
fun CityListItem(
    modifier: Modifier = Modifier,
    data: CityListItemData,
    onDetailsClicked: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit
) {
    CoreOutlinedSurface(
        modifier = modifier,
        borderColor = when (data.isSelected) {
            true -> MaterialTheme.colorScheme.primary
            false -> MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        },
        surfaceColor = when (data.isSelected) {
            true -> MaterialTheme.colorScheme.onPrimaryContainer
            false -> MaterialTheme.colorScheme.surface
        },
        contentColor = when (data.isSelected) {
            true -> MaterialTheme.colorScheme.primaryContainer
            false -> MaterialTheme.colorScheme.onSurface
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)
            ) {
                Text(
                    text = data.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = data.subtitle,
                    style = MaterialTheme.typography.bodyMedium
                )
                DetailsButton(
                    isSelected = data.isSelected,
                    onClicked = onDetailsClicked
                )
            }
            FavoriteStarButton(
                isFavorite = data.isFavorite,
                onClicked = onFavoriteClicked
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
        colors = ButtonDefaults.buttonColors(
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
    isFavorite: Boolean,
    onClicked: (Boolean) -> Unit
) {
    Button(
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
            modifier = Modifier.size(Dimensions.IconSize.medium),
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
        isSelected = false
    ),
    CityListItemData(
        id = 1,
        title = "Paris - FR",
        subtitle = "Coordinates",
        isFavorite = false,
        isSelected = true
    ),
    CityListItemData(
        id = 2,
        title = "New York - US",
        subtitle = "Coordinates",
        isFavorite = false,
        isSelected = false
    )
)

@Preview
@Composable
private fun Preview() {
    CorePreview {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)) {
            cityPreviewItems.forEach { data ->
                CityListItem(
                    data = data,
                    onDetailsClicked = {},
                    onFavoriteClicked = {}
                )
            }
        }
    }
}
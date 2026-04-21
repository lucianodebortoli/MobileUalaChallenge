package com.ldb.mobileualachallenge.feature.cities.presentation.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.Spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LocationIcon()
                Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.extraSmall)) {
                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = data.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Row {
                FavoriteStarButton(
                    isFavorite = data.isFavorite,
                    isSelected = isSelected,
                    onClicked = onFavoriteClicked
                )
                DetailsButton(
                    isSelected = isSelected,
                    onClicked = onDetailsClicked
                )
            }
        }
    }
}

@Composable
private fun LocationIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.height(Dimensions.IconSize.medium)
    ) {
        Icon(
            modifier = Modifier.fillMaxHeight(),
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location Button",
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Composable
private fun DetailsButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier.minimumInteractiveComponentSize(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = when (isSelected) {
                true -> MaterialTheme.colorScheme.primaryContainer
                false -> MaterialTheme.colorScheme.onPrimaryContainer
            }
        ),
        onClick = onClicked
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Details Button",
        )
    }
}

@Composable
private fun FavoriteStarButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    isSelected: Boolean,
    onClicked: (Boolean) -> Unit
) {
    Button(
        modifier = modifier.minimumInteractiveComponentSize(),
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
                true -> when (isSelected) {
                    true -> MaterialTheme.colorScheme.onTertiaryContainer
                    false -> MaterialTheme.colorScheme.tertiary
                }
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
package com.ldb.mobileualachallenge.core.presentation.component.field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CoreSearchFavoritesField(
    modifier: Modifier = Modifier,
    value: String,
    onlyFavorites: Boolean = false,
    isEnabled: Boolean = true,
    padding: PaddingValues = PaddingValues(Dimensions.Spacing.medium),
    onValueChange: (String) -> Unit,
    onFavoriteClicked: () -> Unit,
    onKeyboardSearchClicked: () -> Unit = {},
) {
    CoreSearchField(
        modifier = modifier,
        value = value,
        isEnabled = isEnabled,
        padding = padding,
        trailingIcon = when (onlyFavorites) {
            true -> Icons.Default.Star
            false -> Icons.Default.StarBorder
        },
        iconColors = CoreFieldDefaults.iconColors().copy(
            trailingIconColor = when(onlyFavorites) {
                true -> MaterialTheme.colorScheme.tertiary
                false -> MaterialTheme.colorScheme.onSurfaceVariant
            }
        ),
        onValueChange = onValueChange,
        onTrailingIconClicked = onFavoriteClicked,
        onKeyboardSearchClicked = onKeyboardSearchClicked
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)) {
            CoreSearchFavoritesField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onlyFavorites = false,
                onValueChange = {},
                onFavoriteClicked = {},
            )
            CoreSearchFavoritesField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onlyFavorites = true,
                onValueChange = {},
                onFavoriteClicked = {},
            )
        }
    }
}
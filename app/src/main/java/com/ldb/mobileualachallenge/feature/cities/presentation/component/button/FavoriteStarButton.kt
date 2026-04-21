package com.ldb.mobileualachallenge.feature.cities.presentation.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ldb.mobileualachallenge.core.domain.extension.toggled

@Composable
fun FavoriteStarButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
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
                true -> MaterialTheme.colorScheme.tertiary
                false -> MaterialTheme.colorScheme.outlineVariant
            }
        )
    }
}
package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ldb.mobileualachallenge.core.presentation.component.image.CoreAsyncImage
import com.ldb.mobileualachallenge.feature.cities.presentation.component.button.FavoriteStarButton

data class CityImageData(
    val title: String? = null,
    val url: String? = null,
    val isFavorite: Boolean? = null
)

@Composable
fun CityImageSection(
    modifier: Modifier = Modifier,
    data: CityImageData,
    onFavoriteClicked: (Boolean) -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        CoreAsyncImage(
            imageUrl = data.url
        )
        data.title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge
            )
        }
        data.isFavorite?.let {
            FavoriteStarButton(
                isFavorite = data.isFavorite,
                onClicked = onFavoriteClicked
            )
        }
    }
}

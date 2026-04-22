package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ldb.mobileualachallenge.core.presentation.component.image.CoreAsyncImage
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.feature.cities.presentation.component.button.FavoriteStarButton

data class CityImageData(
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
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            imageUrl = data.url
        )
        data.isFavorite?.let {
            FavoriteStarButton(
                modifier = Modifier.align(Alignment.TopEnd),
                isFavorite = data.isFavorite,
                onClicked = onFavoriteClicked
            )
        }
    }
}

internal fun previewCityImageData() = CityImageData(
    url = null,
    isFavorite = false
)

@Preview
@Composable
private fun Preview() {
    CorePreview(0.dp) {
        CityImageSection(
            modifier = Modifier.fillMaxWidth(),
            data = previewCityImageData(),
            onFavoriteClicked = {}
        )
    }
}
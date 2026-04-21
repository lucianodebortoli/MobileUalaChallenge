package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.map.CoreMap
import com.ldb.mobileualachallenge.core.domain.model.CoreMarker
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun CityMapSection(
    modifier: Modifier = Modifier,
    marker: CoreMarker?
) {
    CoreMap(
        modifier = modifier,
        focusMarker = marker
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CityMapSection(
            marker = null
        )
    }
}
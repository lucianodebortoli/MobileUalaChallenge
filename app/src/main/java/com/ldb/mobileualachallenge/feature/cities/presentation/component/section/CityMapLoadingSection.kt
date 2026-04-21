package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.component.progress.CoreProgressBar


@Composable
fun CityMapLoadingSection(
    modifier: Modifier = Modifier
) {
    CoreProgressBar(
        modifier = modifier,
        title = stringResource(R.string.feature_cities_map_loading)
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CityMapLoadingSection(
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.error.CoreErrorView
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun LoadCitiesErrorSection(
    modifier: Modifier,
    onRetryClicked: () -> Unit
) {
    CoreErrorView(
        modifier = modifier,
        title = stringResource(R.string.feature_cities_error_load_list),
        onRetryClicked = onRetryClicked
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        LoadCitiesErrorSection(
            modifier = Modifier.fillMaxSize(),
            onRetryClicked = {}
        )
    }
}
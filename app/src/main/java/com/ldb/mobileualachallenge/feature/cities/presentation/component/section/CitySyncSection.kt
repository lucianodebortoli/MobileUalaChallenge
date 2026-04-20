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
fun CitySyncSection(
    modifier: Modifier = Modifier
) {
    CoreProgressBar(
        modifier = modifier,
        title = stringResource(R.string.feature_cities_list_syncing)
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CitySyncSection(
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.domain.model.CoreMarker
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityMapSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityMapErrorSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityMapLoadingSection


@Composable
fun CityMapScreen(
    viewModel: CityMapViewModel = hiltViewModel(),
    onScreenResult: (CityMapResult) -> Unit
) {
    val mapState by viewModel.mapState.collectAsStateWithLifecycle()
    val cityName by viewModel.cityName.collectAsStateWithLifecycle()
    val cityMarker by viewModel.cityMarker.collectAsStateWithLifecycle()
    CityMapLayout(
        mapState = mapState,
        cityName = cityName,
        cityMarker = cityMarker,
        onBackClicked = { onScreenResult(CityMapResult.NavigateBack) },
        onRetryClicked = { viewModel.onEvent(CityMapEvent.OnLoadRetryClicked) }
    )
}

@Composable
private fun CityMapLayout(
    mapState: CityMapState,
    cityName: String?,
    cityMarker: CoreMarker?,
    onBackClicked: () -> Unit,
    onRetryClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = cityName ?: stringResource(R.string.feature_cities_map_title),
                onBackClicked = onBackClicked
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumeWindowInsets(WindowInsets.safeDrawing)
        ) {
            CityMapContent(
                mapState = mapState,
                cityMarker = cityMarker,
                onRetryClicked = onRetryClicked
            )
        }
    }
}

@Composable private fun CityMapContent(
    mapState: CityMapState,
    cityMarker: CoreMarker?,
    onRetryClicked: () -> Unit
) {
    when (mapState) {
        CityMapState.Error -> CityMapErrorSection(
            modifier = Modifier.fillMaxSize(),
            onRetryClicked = onRetryClicked
        )
        CityMapState.Loading -> CityMapLoadingSection(
            modifier = Modifier.fillMaxSize()
        )
        CityMapState.Ready -> CityMapSection(
            modifier = Modifier.fillMaxSize(),
            marker = cityMarker
        )
    }
}
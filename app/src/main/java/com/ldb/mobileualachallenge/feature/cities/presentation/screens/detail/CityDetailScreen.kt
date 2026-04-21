package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.framework.AdaptiveOrientation
import com.ldb.mobileualachallenge.core.framework.getAdaptiveOrientation
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail

@Composable
fun CityDetailScreen(
    viewModel: CityDetailViewModel = hiltViewModel(),
    onScreenResult: (CityDetailResult) -> Unit
) {
    val configuration = LocalConfiguration.current
    val orientation = configuration.getAdaptiveOrientation()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val cityDetail by viewModel.cityDetail.collectAsStateWithLifecycle()
    CityDetailContent(
        orientation = orientation,
        isLoading = isLoading,
        cityDetail = cityDetail,
        onBackClicked = { onScreenResult(CityDetailResult.NavigateBack) },
    )
}

@Composable
private fun CityDetailContent(
    orientation: AdaptiveOrientation,
    isLoading: Boolean,
    cityDetail: CityDetail?,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = cityDetail?.city?.name ?: stringResource(R.string.feature_cities_detail_title),
                onBackClicked = onBackClicked
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        val rootModifier = Modifier
            .padding(paddingValues)
            .consumeWindowInsets(WindowInsets.safeDrawing)
        when (orientation) {
            AdaptiveOrientation.Portrait -> PortraitCityDetailContent(
                modifier = rootModifier,
                isLoading = isLoading,
            )
            AdaptiveOrientation.Landscape -> LandscapeCityDetailContent(
                modifier = rootModifier,
                isLoading = isLoading,
            )
        }
    }
}

@Composable
private fun PortraitCityDetailContent(
    modifier: Modifier,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        Text("Portrait Screen")
    }
}

@Composable
private fun LandscapeCityDetailContent(
    modifier: Modifier,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier
    ) {
        Text("Landscape Screen")
    }
}
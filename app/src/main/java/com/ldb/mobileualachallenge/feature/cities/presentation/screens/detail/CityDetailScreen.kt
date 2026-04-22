package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.framework.AdaptiveOrientation
import com.ldb.mobileualachallenge.core.framework.getAdaptiveOrientation
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityDescriptionData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityDescriptionSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityImageData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityImageSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.LoadCityErrorSection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.LoadingCitySection
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.previewCityDescriptionData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.previewCityImageData

@Composable
fun CityDetailScreen(
    viewModel: CityDetailViewModel = hiltViewModel(),
    onScreenResult: (CityDetailResult) -> Unit
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val orientation = configuration.getAdaptiveOrientation()
    val snackbarHostState = remember { SnackbarHostState() }
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()
    val cityName by viewModel.cityName.collectAsStateWithLifecycle()
    val cityDescriptionData by viewModel.cityDescriptionData.collectAsStateWithLifecycle()
    val cityImageData by viewModel.cityImageData.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.actions.collect { action ->
            when (action) {
                is CityDetailAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = context.getString(action.stringRes)
                    )
                }
            }
        }
    }
    CityDetailContent(
        orientation = orientation,
        detailState = detailState,
        cityName = cityName,
        cityDescriptionData = cityDescriptionData,
        cityImageData = cityImageData,
        onBackClicked = { onScreenResult(CityDetailResult.NavigateBack) },
        onFavoriteClicked = { viewModel.onEvent(CityDetailEvent.OnFavoriteChanged(it)) },
        onRetryClicked = { viewModel.onEvent(CityDetailEvent.OnLoadRetryClicked) }
    )
}

@Composable
private fun CityDetailContent(
    orientation: AdaptiveOrientation,
    detailState: CityDetailState,
    cityDescriptionData: CityDescriptionData,
    cityImageData: CityImageData,
    cityName: String?,
    onBackClicked: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onRetryClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = cityName ?: stringResource(R.string.feature_cities_detail_title),
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
                detailState = detailState,
                cityDescriptionData = cityDescriptionData,
                cityImageData = cityImageData,
                onFavoriteClicked = onFavoriteClicked,
                onRetryClicked = onRetryClicked
            )
            AdaptiveOrientation.Landscape -> LandscapeCityDetailContent(
                modifier = rootModifier,
                detailState = detailState,
                cityDescriptionData = cityDescriptionData,
                cityImageData = cityImageData,
                onFavoriteClicked = onFavoriteClicked,
                onRetryClicked = onRetryClicked
            )
        }
    }
}

@Composable
private fun PortraitCityDetailContent(
    modifier: Modifier,
    detailState: CityDetailState,
    cityDescriptionData: CityDescriptionData,
    cityImageData: CityImageData,
    onFavoriteClicked: (Boolean) -> Unit,
    onRetryClicked: () -> Unit
) {
    when (detailState) {
        CityDetailState.Error -> LoadCityErrorSection(
            modifier = modifier.fillMaxSize(),
            onRetryClicked = onRetryClicked
        )
        CityDetailState.Loading -> LoadingCitySection(
            modifier = modifier.fillMaxSize()
        )
        CityDetailState.Ready -> Column(
            modifier = modifier
        ) {
            CityImageSection(
                modifier = Modifier.fillMaxWidth(),
                data = cityImageData,
                onFavoriteClicked = onFavoriteClicked,
            )
            CityDescriptionSection(
                data = cityDescriptionData
            )
        }
    }
}

@Composable
private fun LandscapeCityDetailContent(
    modifier: Modifier,
    detailState: CityDetailState,
    cityDescriptionData: CityDescriptionData,
    cityImageData: CityImageData,
    onFavoriteClicked: (Boolean) -> Unit,
    onRetryClicked: () -> Unit
) {
    when (detailState) {
        CityDetailState.Error -> LoadCityErrorSection(
            modifier = modifier.fillMaxSize(),
            onRetryClicked = onRetryClicked
        )
        CityDetailState.Loading -> LoadingCitySection(
            modifier = modifier.fillMaxSize()
        )
        CityDetailState.Ready -> Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CityImageSection(
                modifier = Modifier
                    .weight(0.5f)
                    .clip(shape = RoundedCornerShape(Dimensions.CornerRadius.medium))
                    .shadow(
                        elevation = Dimensions.Elevation.medium,
                        shape = RoundedCornerShape(Dimensions.CornerRadius.medium)
                    ),
                data = cityImageData,
                onFavoriteClicked = onFavoriteClicked
            )
            CityDescriptionSection(
                modifier = Modifier.weight(0.5f),
                data = cityDescriptionData
            )
        }
    }
}

@Preview(name = "Portrait", device = "spec:width=411dp,height=891dp,orientation=portrait" )
@Preview(name = "Landscape", device = "spec:width=411dp,height=891dp,orientation=landscape" )
@Composable
private fun Preview() {
    CorePreview(padding = 0.dp) {
        CityDetailContent(
            orientation = AdaptiveOrientation.Portrait,
            detailState = CityDetailState.Ready,
            cityDescriptionData = previewCityDescriptionData(),
            cityImageData = previewCityImageData(),
            cityName = "Some city",
            onBackClicked = {},
            onRetryClicked = {},
            onFavoriteClicked = {}
        )
    }
}
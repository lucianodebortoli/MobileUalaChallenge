package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar
import com.ldb.mobileualachallenge.feature.cities.domain.model.City


@Composable
fun CityMapScreen(
    viewModel: CityMapViewModel = hiltViewModel(),
    onScreenResult: (CityMapResult) -> Unit
) {
    val city by viewModel.city.collectAsStateWithLifecycle()
    CityMapContent(
        city = city,
        onBackClicked = { onScreenResult(CityMapResult.NavigateBack) },
    )
}

@Composable
private fun CityMapContent(
    city: City?,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = city?.name ?: stringResource(R.string.feature_cities_map_title),
                onBackClicked = onBackClicked
            )
        },
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(WindowInsets.navigationBars)
        ) {
            // TODO map section
        }
    }
}
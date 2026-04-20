package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar

@Composable
fun CityDetailScreen(navController: NavController) {
    val viewModel: CityDetailViewModel = hiltViewModel()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    CityDetailContent(
        isLoading = isLoading,
        onBackClicked = { navController.popBackStack() },
    )
}

@Composable
private fun CityDetailContent(
    isLoading: Boolean,
    onBackClicked: () -> Unit
) {
    val configuration = LocalConfiguration.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CoreTopBar(
                title = "TODO",
                onBackClicked = onBackClicked
            )
        },
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        val rootModifier = Modifier
            .padding(paddingValues)
            .consumeWindowInsets(WindowInsets.navigationBars)
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeCityDetailContent(
                modifier = rootModifier,
                isLoading = isLoading,
            )
            else -> PortraitCityDetailContent(
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
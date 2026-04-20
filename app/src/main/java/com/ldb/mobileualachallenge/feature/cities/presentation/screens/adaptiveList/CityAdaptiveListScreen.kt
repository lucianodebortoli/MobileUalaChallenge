package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ldb.mobileualachallenge.core.presentation.component.topbar.CoreTopBar

@Composable
fun CityAdaptiveListScreen(navController: NavController) {
    val viewModel: CityAdaptiveListViewModel = hiltViewModel()
    AdaptiveCityListContent(
        onBackClicked = { navController.popBackStack() }
    )
}

@Composable
private fun AdaptiveCityListContent(
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
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeCityListContent(
                modifier = rootModifier
            )
            else -> PortraitCityListContent(
                modifier = rootModifier
            )
        }
    }
}

@Composable
private fun PortraitCityListContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Portrait Screen")
    }
}

@Composable
private fun LandscapeCityListContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Landscape Screen")
    }
}
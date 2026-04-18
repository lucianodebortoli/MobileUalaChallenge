package com.ldb.mobileualachallenge.feature.cities.presentation.screens.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.ldb.mobileualachallenge.core.presentation.component.CustomTopBar

@Composable
fun CityListScreen(navController: NavController) {
    AdaptiveCityListContent(
        onBackClicked = { navController.popBackStack() }
    )
}

@Composable
private fun AdaptiveCityListContent(
    onBackClicked: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val viewModel: CityListViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            CustomTopBar(
                title = "TODO",
                onBackClicked = onBackClicked
            )
        }
    ) { paddingValues ->
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeCityListContent(
                modifier = Modifier.padding(paddingValues)
            )
            else -> PortraitCityListContent(
                modifier = Modifier.padding(paddingValues)
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
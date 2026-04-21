package com.ldb.mobileualachallenge.main.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList.CityAdaptiveListResult
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList.CityAdaptiveListScreen
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail.CityDetailResult
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail.CityDetailScreen
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.map.CityMapResult
import com.ldb.mobileualachallenge.feature.cities.presentation.screens.map.CityMapScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.CityList
    ) {
        composable<Routes.CityList> {
            CityAdaptiveListScreen(
                onScreenResult = { result ->
                    when (result) {
                        is CityAdaptiveListResult.NavigateToCityDetail -> {
                            navController.navigate(Routes.CityDetail(cityId = result.cityId))
                        }
                        is CityAdaptiveListResult.NavigateToCityMap -> {
                            navController.navigate(Routes.CityMap(cityId = result.cityId))
                        }
                    }
                }
            )
        }
        composable<Routes.CityMap> {
            CityMapScreen(
                onScreenResult = { result ->
                    when (result) {
                        is CityMapResult.NavigateBack -> navController.popBackStack()
                    }
                }
            )
        }
        composable<Routes.CityDetail> {
            CityDetailScreen(
                onScreenResult = { result ->
                    when (result) {
                        is CityDetailResult.NavigateBack -> navController.popBackStack()
                    }
                }
            )
        }
    }
}

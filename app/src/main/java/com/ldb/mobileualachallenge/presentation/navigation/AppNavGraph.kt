package com.ldb.mobileualachallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ldb.mobileualachallenge.presentation.feature.cities.detail.CityDetailScreen
import com.ldb.mobileualachallenge.presentation.feature.cities.list.CityListScreen
import com.ldb.mobileualachallenge.presentation.feature.cities.map.CityMapScreen

// TODO add navArguments
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.CITY_LIST
    ) {
        composable(route = Routes.CITY_LIST) {
            CityListScreen(navController)
        }
        composable(route = Routes.CITY_MAP) {
            CityMapScreen(navController)
        }
        composable(route = Routes.CITY_DETAIL) {
            CityDetailScreen(navController)
        }
    }
}
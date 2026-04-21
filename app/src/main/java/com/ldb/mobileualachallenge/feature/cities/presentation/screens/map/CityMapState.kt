package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

sealed class CityMapState {
    data object Loading : CityMapState()
    data object Error : CityMapState()
    data object Ready : CityMapState()
}
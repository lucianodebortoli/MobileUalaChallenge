package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

sealed class CityDetailState {
    data object Loading : CityDetailState()
    data object Error : CityDetailState()
    data object Ready : CityDetailState()
}
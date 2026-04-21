package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map


sealed class CityMapEvent {

    data object OnLoadRetryClicked : CityMapEvent()

}
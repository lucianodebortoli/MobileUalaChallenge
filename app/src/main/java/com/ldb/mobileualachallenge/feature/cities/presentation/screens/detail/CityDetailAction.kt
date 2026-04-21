package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail


sealed class CityDetailAction {
    data class ShowSnackbar(val stringRes: Int) : CityDetailAction()
}
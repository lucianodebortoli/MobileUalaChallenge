package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList.CityAdaptiveListEvent


sealed class CityDetailEvent {

    data object OnLoadRetryClicked : CityDetailEvent()
    data class OnFavoriteChanged(val isFavorite: Boolean) : CityDetailEvent()

}
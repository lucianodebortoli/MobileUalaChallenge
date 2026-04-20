package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

sealed class CityAdaptiveListEvent {

    data class OnDetailsClicked(val cityId: CityId) : CityAdaptiveListEvent()
    data class OnFavoriteClicked(val cityId: CityId, val isFavorite: Boolean) : CityAdaptiveListEvent()
    data class OnSearchQueryChanged(val query: String) : CityAdaptiveListEvent()

    data object OnFilterButtonClicked : CityAdaptiveListEvent()

}
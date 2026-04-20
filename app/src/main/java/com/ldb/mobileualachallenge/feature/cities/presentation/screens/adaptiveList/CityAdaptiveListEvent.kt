package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

sealed class CityAdaptiveListEvent {

    data class OnCityDetailsClicked(val cityId: CityId) : CityAdaptiveListEvent()
    data class OnCityFavoriteClicked(val cityId: CityId, val isFavorite: Boolean) : CityAdaptiveListEvent()
    data class OnSearchQueryChanged(val query: String) : CityAdaptiveListEvent()

    data object OnSyncRetryClicked : CityAdaptiveListEvent()
    data object OnFilterButtonClicked : CityAdaptiveListEvent()

}
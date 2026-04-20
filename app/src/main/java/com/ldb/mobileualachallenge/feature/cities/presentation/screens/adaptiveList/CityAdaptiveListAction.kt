package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

sealed class CityAdaptiveListAction {
    data class ShowSnackbar(val stringRes: Int) : CityAdaptiveListAction()
    data class NavigateToMap(val city: City) : CityAdaptiveListAction()
    data class NavigateToDetail(val cityId: CityId) : CityAdaptiveListAction()
}
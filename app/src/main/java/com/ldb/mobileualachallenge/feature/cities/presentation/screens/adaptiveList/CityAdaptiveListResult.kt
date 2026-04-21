package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId

sealed class CityAdaptiveListResult {

    data class NavigateToCityDetail(val cityId: CityId) : CityAdaptiveListResult()
    data class NavigateToCityMap(val cityId: CityId) : CityAdaptiveListResult()

}
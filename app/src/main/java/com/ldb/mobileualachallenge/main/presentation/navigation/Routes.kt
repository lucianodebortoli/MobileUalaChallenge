package com.ldb.mobileualachallenge.main.presentation.navigation

import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object CityList : Routes

    @Serializable
    data class CityMap(val cityId: CityId) : Routes

    @Serializable
    data class CityDetail(val cityId: CityId) : Routes

}

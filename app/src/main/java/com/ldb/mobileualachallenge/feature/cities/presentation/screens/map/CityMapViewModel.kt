package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityUseCase
import com.ldb.mobileualachallenge.main.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class CityMapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCityUseCase: GetCityUseCase
) : ViewModel() {

    val cityId: CityId = savedStateHandle.toRoute<Routes.CityMap>().cityId

    private val _city = MutableStateFlow<City?>(value = null)
    val city = _city.asStateFlow()

}

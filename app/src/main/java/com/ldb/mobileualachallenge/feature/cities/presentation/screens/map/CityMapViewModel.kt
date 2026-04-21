package com.ldb.mobileualachallenge.feature.cities.presentation.screens.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityUseCase
import com.ldb.mobileualachallenge.feature.cities.presentation.mapper.toMarker
import com.ldb.mobileualachallenge.main.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityMapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCityUseCase: GetCityUseCase
) : ViewModel() {

    val cityId: CityId = savedStateHandle.toRoute<Routes.CityMap>().cityId

    private val _mapState = MutableStateFlow<CityMapState>(CityMapState.Loading)
    val mapState = _mapState.asStateFlow()

    private val _city = MutableStateFlow<City?>(value = null)

    val cityName = _city.map { city ->
        city?.name
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val cityMarker = _city.map { city ->
        city?.toMarker()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        fetchCity(cityId)
    }

    fun onEvent(event: CityMapEvent) {
        when (event) {
            CityMapEvent.OnLoadRetryClicked -> onLoadRetryClicked()
        }
    }

    private fun onLoadRetryClicked() {
        fetchCity(cityId)
    }

    private fun fetchCity(cityId: CityId) = viewModelScope.launch {
        _mapState.value = CityMapState.Loading
        getCityUseCase(cityId).fold(
            onSuccess = { city ->
                _city.value = city
                _mapState.value = CityMapState.Ready
            },
            onFailure = {
                _mapState.value = CityMapState.Error
            }
        )
    }

}

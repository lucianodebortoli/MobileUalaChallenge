package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityDetailUseCase
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.MarkCityAsFavoriteUseCase
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityDescriptionData
import com.ldb.mobileualachallenge.feature.cities.presentation.component.section.CityImageData
import com.ldb.mobileualachallenge.main.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCityDetailUseCase: GetCityDetailUseCase,
    private val markCityAsFavoriteUseCase: MarkCityAsFavoriteUseCase
) : ViewModel() {

    val cityId: CityId = savedStateHandle.toRoute<Routes.CityDetail>().cityId

    private val _actions = Channel<CityDetailAction>()
    val actions = _actions.receiveAsFlow()

    private val _detailState = MutableStateFlow<CityDetailState>(CityDetailState.Loading)
    val detailState = _detailState.asStateFlow()

    private val _cityDetail = MutableStateFlow<CityDetail?>(value = null)

    val cityName = _cityDetail.map { detail ->
        detail?.city?.name
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val cityDescriptionData: StateFlow<CityDescriptionData> = _cityDetail.map { detail ->
        CityDescriptionData(
            summary = detail?.additionalInfo?.shortDescription,
            description = detail?.additionalInfo?.longDescription
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CityDescriptionData()
    )

    val cityImageData: StateFlow<CityImageData> = _cityDetail.map { detail ->
        CityImageData(
            title = detail?.city?.name,
            url = detail?.additionalInfo?.imageUrl,
            isFavorite = detail?.city?.isFavorite
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CityImageData()
    )

    init {
        fetchCityDetail(cityId)
    }

    fun onEvent(event: CityDetailEvent) {
        when (event) {
            CityDetailEvent.OnLoadRetryClicked -> onLoadRetryClicked()
            is CityDetailEvent.OnFavoriteChanged -> onFavoriteClicked(event.isFavorite)
        }
    }

    private fun onFavoriteClicked(favorite: Boolean) = viewModelScope.launch {
        markCityAsFavoriteUseCase(
            cityId = cityId,
            isFavorite = favorite
        ).fold(
            onSuccess = {
                fetchCityDetail(cityId)
            },
            onFailure = {
                _actions.send(
                    CityDetailAction.ShowSnackbar(
                        stringRes = R.string.feature_cities_error_mark_favorite
                    )
                )
            }
        )
    }

    private fun onLoadRetryClicked() {
        fetchCityDetail(cityId)
    }

    private fun fetchCityDetail(cityId: CityId) = viewModelScope.launch {
        _detailState.value = CityDetailState.Loading
        getCityDetailUseCase(cityId).fold(
            onSuccess = { detail ->
                _cityDetail.value = detail
                _detailState.value = CityDetailState.Ready
            },
            onFailure = {
                _detailState.value = CityDetailState.Error
            }
        )
    }

}

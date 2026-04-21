package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityDetailUseCase
import com.ldb.mobileualachallenge.main.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class CityDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCityDetailUseCase: GetCityDetailUseCase
) : ViewModel() {

    val cityId: CityId = savedStateHandle.toRoute<Routes.CityDetail>().cityId

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _cityDetail = MutableStateFlow<CityDetail?>(value = null)
    val cityDetail = _cityDetail.asStateFlow()

}

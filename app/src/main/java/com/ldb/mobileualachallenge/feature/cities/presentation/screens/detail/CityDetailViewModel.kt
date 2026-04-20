package com.ldb.mobileualachallenge.feature.cities.presentation.screens.detail

import androidx.lifecycle.ViewModel
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCityDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CityDetailViewModel @Inject constructor(
    getCityDetailUseCase: GetCityDetailUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

}
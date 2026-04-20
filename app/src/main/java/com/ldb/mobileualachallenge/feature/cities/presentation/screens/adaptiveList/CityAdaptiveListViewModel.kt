package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityId
import com.ldb.mobileualachallenge.feature.cities.domain.usecase.GetCitiesUseCase
import com.ldb.mobileualachallenge.feature.cities.presentation.component.item.CityListItemData
import com.ldb.mobileualachallenge.feature.cities.presentation.mapper.toItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CityAdaptiveListViewModel @Inject constructor(
    getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _favoritesOnly = MutableStateFlow(false)
    val favoritesOnly = _favoritesOnly.asStateFlow()

    private val _selectedCityId = MutableStateFlow<CityId?>(null)
    val selectedCityId = _selectedCityId.asStateFlow()

    /**
     * The flow of paginated items.
     * Reacts to [favoritesOnly] and [searchQuery].
     *
     * If necessary:
     * Implements a small debounce delay of 100 ms to prevent too frequent updates, while still being responsive.
     */
    val citiesPager: Flow<PagingData<CityListItemData>> = searchQuery
/*        .transformLatest { latestQuery ->
            delay(timeMillis = 100L)
            emit(value = latestQuery)
        }*/
        .combine(flow = favoritesOnly) { query, fav ->
            Pair(query, fav)
        }
        .distinctUntilChanged()
        .flatMapLatest { (queryValue, onlyFavoritesValue) ->
            getCitiesUseCase(
                searchQuery = queryValue,
                filterFavorites = onlyFavoritesValue
            )
        }
        .map { pagingData ->
            pagingData.map { it.toItemData() }
        }
        .cachedIn(viewModelScope)

    /**
     * Handles events from the UI layer.
     */
    fun onEvent(event: CityAdaptiveListEvent) {
        when (event) {
            is CityAdaptiveListEvent.OnFilterButtonClicked -> TODO()
            is CityAdaptiveListEvent.OnDetailsClicked -> TODO()
            is CityAdaptiveListEvent.OnFavoriteClicked -> TODO()
            is CityAdaptiveListEvent.OnSearchQueryChanged -> TODO()
        }
    }

}
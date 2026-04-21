package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

sealed class CityListSyncState {
    data object Syncing : CityListSyncState()
    data object Error : CityListSyncState()
    data object ListReady : CityListSyncState()
}
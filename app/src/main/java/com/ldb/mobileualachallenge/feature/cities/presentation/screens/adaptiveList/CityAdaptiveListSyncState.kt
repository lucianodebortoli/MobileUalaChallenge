package com.ldb.mobileualachallenge.feature.cities.presentation.screens.adaptiveList

sealed class SyncState {
    data object Syncing : SyncState()
    data object Error : SyncState()
    data object ListReady : SyncState()
}
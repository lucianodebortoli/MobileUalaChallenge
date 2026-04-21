package com.ldb.mobileualachallenge.core.presentation.component.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.ldb.mobileualachallenge.core.domain.model.CoreCoordinates
import com.ldb.mobileualachallenge.core.domain.model.CoreMarker

private const val DEFAULT_ZOOM = 10f

@Composable
fun CoreMap(
    modifier: Modifier = Modifier,
    focusMarker: CoreMarker? = null
) {
    val markerPosition = focusMarker?.coordinates?.toLatLng()
    val uiSettings = remember {
        MapUiSettings(
            compassEnabled = true,
            indoorLevelPickerEnabled = false,
            mapToolbarEnabled = false,
            myLocationButtonEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabled = false,
            scrollGesturesEnabledDuringRotateOrZoom = false,
            tiltGesturesEnabled = false,
            zoomControlsEnabled = false,
            zoomGesturesEnabled = false
        )
    }
    val properties = remember {
        MapProperties(
            mapStyleOptions = MapStyleOptions(MapStyle.json)
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        markerPosition?.let {
            position = CameraPosition.fromLatLngZoom(it, DEFAULT_ZOOM)
        }
    }
    LaunchedEffect(key1 = focusMarker) {
        markerPosition?.let {
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(it, DEFAULT_ZOOM)
            )
        }
    }
    GoogleMap(
        modifier = modifier,
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        focusMarker?.let { marker ->
            val position = marker.coordinates.toLatLng()
            val markerState = rememberMarkerState(position = position)
            LaunchedEffect(key1 = position) {
                markerState.position = position
            }
            Marker(
                state = markerState,
                title = marker.title,
            )
        }
    }
}

private fun CoreCoordinates.toLatLng() = LatLng(latitude, longitude)
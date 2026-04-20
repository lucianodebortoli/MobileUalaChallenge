package com.ldb.mobileualachallenge.core.framework

import android.content.res.Configuration

enum class AdaptiveOrientation {
    Portrait,
    Landscape;
}

fun Configuration.getAdaptiveOrientation(): AdaptiveOrientation = when (this.orientation) {
    Configuration.ORIENTATION_LANDSCAPE -> AdaptiveOrientation.Landscape
    else -> AdaptiveOrientation.Portrait
}
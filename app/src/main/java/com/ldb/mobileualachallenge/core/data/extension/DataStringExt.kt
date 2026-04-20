package com.ldb.mobileualachallenge.core.data.extension

import android.text.Html

fun String.cleanHtml(): String {
    return android.text.Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}
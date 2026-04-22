package com.ldb.mobileualachallenge.core.presentation.extension

/**
 * Gets the emoji flag from a country prefix (Eg: AR).
 */
fun String.toFlagEmoji(): String {
    if (this.length != 2) return this
    val countryCode = this.uppercase()
    val firstChar = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
    val secondChar = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
}
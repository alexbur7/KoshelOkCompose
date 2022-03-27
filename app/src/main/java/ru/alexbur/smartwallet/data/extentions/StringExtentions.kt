package ru.alexbur.smartwallet.data.extentions

fun String.formattedMoney(icon: String, startSymbol: String? = null): String {
    return "$startSymbol $this $icon"
}
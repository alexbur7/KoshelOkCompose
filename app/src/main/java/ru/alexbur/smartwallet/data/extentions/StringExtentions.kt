package ru.alexbur.smartwallet.data.extentions

fun String.formattedMoney(icon: String, startSymbol: String? = null): String {
    return "${startSymbol ?: ""} $this $icon"
}

fun String?.defaultMoney(): String{
    if (this == "null") return "0.0"
    return this ?: "0.0"
}
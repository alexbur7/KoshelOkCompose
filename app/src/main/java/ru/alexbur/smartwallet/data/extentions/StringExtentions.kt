package ru.alexbur.smartwallet.data.extentions

fun String.formattedMoney(icon: String, startSymbol: String? = null): String {
    return "${startSymbol ?: ""} $this $icon"
}

fun String?.defaultMoney(): String{
    if (this == null || this == "null") return "0.0"
    val cent = this.substringAfter(".")
    val money = this.substringBefore(".")
    val finalCent = if (cent.length > 2) cent.substring(0, 2) else cent
    return "$money.$finalCent"
}
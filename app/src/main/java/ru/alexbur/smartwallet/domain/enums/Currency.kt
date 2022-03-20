package ru.alexbur.smartwallet.domain.enums

import androidx.annotation.StringRes
import ru.alexbur.smartwallet.R

enum class Currency(val icon: String, @StringRes val nameId: Int) {
    RUB("₽", R.string.rub),
    EUR("€", R.string.eur),
    USD("$", R.string.usd),
    CHF("₣", R.string.chf)
}
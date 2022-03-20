package ru.alexbur.smartwallet.domain.enums

import androidx.annotation.StringRes
import ru.alexbur.smartwallet.R

enum class Currency(val icon: String, @StringRes val nameId: Int, @StringRes val nameListId: Int) {
    RUB("₽", R.string.rub, R.string.rub_list),
    EUR("€", R.string.eur, R.string.eur_list),
    USD("$", R.string.usd, R.string.usd_list),
    CHF("₣", R.string.chf, R.string.chf_list)
}
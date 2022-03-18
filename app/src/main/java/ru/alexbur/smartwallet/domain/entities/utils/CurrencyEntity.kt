package ru.alexbur.smartwallet.domain.entities.utils

import ru.alexbur.smartwallet.domain.enums.Currency

data class CurrencyEntity(
    var currency: Currency,
    var isEnable: Boolean
)
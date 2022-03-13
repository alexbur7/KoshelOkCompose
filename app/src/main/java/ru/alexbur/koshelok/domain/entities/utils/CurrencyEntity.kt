package ru.alexbur.koshelok.domain.entities.utils

import ru.alexbur.koshelok.domain.enums.Currency

data class CurrencyEntity(
    var currency: Currency,
    var isEnable: Boolean
)
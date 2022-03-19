package ru.alexbur.smartwallet.domain.entities.listwallet

import ru.alexbur.smartwallet.domain.enums.Currency

data class CurrencyEntity(
    val name: Currency,
    val course: String,
    val isUp: Boolean
)
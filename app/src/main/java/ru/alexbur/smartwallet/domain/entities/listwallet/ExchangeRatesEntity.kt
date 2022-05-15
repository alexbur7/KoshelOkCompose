package ru.alexbur.smartwallet.domain.entities.listwallet

import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

data class ExchangeRatesEntity(
    val firstCurrency: CurrencyEntity,
    val secondCurrency: CurrencyEntity,
    val thirdCurrency: CurrencyEntity
)
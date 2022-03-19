package ru.alexbur.smartwallet.domain.entities.listwallet

data class ExchangeRatesEntity(
    val firstCurrency: CurrencyEntity,
    val secondCurrency: CurrencyEntity,
    val thirdCurrency: CurrencyEntity
)
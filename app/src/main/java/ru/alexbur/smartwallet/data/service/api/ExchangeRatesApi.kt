package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRatesApi(
    val firstCurrency: CurrencyApi,
    val secondCurrency: CurrencyApi,
    val thirdCurrency: CurrencyApi
)
package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainScreenDataApi(
    @SerialName("balance")
    val balance: BalanceApi,
    @SerialName("currencyDto")
    val exchangeRatesApi: ExchangeRatesApi,
    @SerialName("wallets")
    val wallets: List<WalletApi>
)
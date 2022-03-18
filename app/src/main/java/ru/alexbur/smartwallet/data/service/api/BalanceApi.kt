package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.Serializable

@Serializable
data class BalanceApi(
    val amountMoney: String,
    val incomeMoney: String,
    val consumptionMoney: String
)

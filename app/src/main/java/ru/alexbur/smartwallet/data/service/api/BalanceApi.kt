package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BalanceApi(
    @SerialName("amount")
    val amountMoney: String? = null,
    @SerialName("income")
    val incomeMoney: String? = null,
    @SerialName("expense")
    val consumptionMoney: String? = null
)

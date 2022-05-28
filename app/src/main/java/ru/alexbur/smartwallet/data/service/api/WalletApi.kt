package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletApi(
    @SerialName("wallet_id")
    val id: Long,
    val name: String,
    @SerialName("amount")
    val amountMoney: String?,
    @SerialName("income")
    val income: String?,
    @SerialName("expense")
    val consumption: String?,
    val limit: String?,
    val currency: CurrencyApi,
    @SerialName("is_hide")
    val isHide: Boolean
)
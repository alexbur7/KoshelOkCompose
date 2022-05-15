package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WalletApi(
    @SerialName("walletId")
    val id: Long,
    val name: String,
    val amountMoney: String?,
    val income: String?,
    val consumption: String?,
    val limit: String?,
    val currency: CurrencyApi,
    val isHide: Boolean
)
package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionApi(
    @SerialName("transaction_id")
    val id: Long? = null,
    @SerialName("value")
    val money: String? = null,
    val category: CategoryApi,
    val currency: CurrencyApi,
    @SerialName("transaction_time")
    val time: Long = System.currentTimeMillis(),
    @SerialName("wallet_id")
    val walletId: Long? = null
)
package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionApi(
    @SerialName("transactionId")
    val id: Long,
    @SerialName("value")
    val money: String,
    val category: CategoryApi,
    val currency: CurrencyApi,
    val time: Long
)
package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateWalletApi(
    val name: String,
    @SerialName("amount")
    val amountMoney: String,
    val income: String,
    val consumption: String,
    val limit: String?,
    @SerialName("currency_id")
    val currencyId: Long,
    @SerialName("is_hide")
    val isHide: Boolean
)
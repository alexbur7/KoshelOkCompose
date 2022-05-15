package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.Serializable

@Serializable
data class CreateWalletApi(
    val name: String,
    val amountMoney: String,
    val income: String,
    val consumption: String,
    val limit: String?,
    val currencyId: Long,
    val isHide: Boolean
)
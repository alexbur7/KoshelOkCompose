package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.Serializable

@Serializable
data class DetailWalletApi(
    val walletApi: WalletApi,
    val transactions: List<TransactionApi>
)
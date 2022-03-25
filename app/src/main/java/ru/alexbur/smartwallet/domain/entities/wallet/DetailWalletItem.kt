package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.enums.Currency

sealed class DetailWalletItem {

    data class Day(val day: String) : DetailWalletItem()

    data class Transaction(
        val id: Long,
        val category: CategoryEntity,
        val money: String,
        val time: String,
        val day: String,
        val currency: Currency
    ) : DetailWalletItem()
}
package ru.alexbur.smartwallet.domain.entities.wallet

import androidx.annotation.FloatRange
import ru.alexbur.smartwallet.domain.enums.Currency

data class WalletEntity(
    val id: Long,
    val name: String,
    val amountMoney: String,
    val incomeMoney: String,
    val consumptionMoney: String,
    val currency: Currency,
    val isHide: Boolean,
    val limit: String?,
    @FloatRange(from = 0.0, to = 1.0)
    val partSpending: Float?
) {

    companion object {
        val shimmerData = listOf(
            WalletEntity(
                0, "", "", "", "", Currency.RUB, false, null, 0f
            )
        )
    }
}

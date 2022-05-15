package ru.alexbur.smartwallet.domain.entities.wallet

import androidx.annotation.FloatRange
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

data class WalletEntity(
    val id: Long,
    val name: String,
    val amountMoney: String,
    val incomeMoney: String,
    val consumptionMoney: String,
    val currency: CurrencyEntity,
    val isHide: Boolean,
    val limit: String?,
    @FloatRange(from = 0.0, to = 1.0)
    val partSpending: Float?
) {

    companion object {
        val shimmerData = listOf(
            WalletEntity(
                0, "", "", "", "", CurrencyEntity.default, false, null, 0f
            )
        )
    }
}

package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

data class CreateWalletEntity(
    val id: Long? = null,
    val limit: String? = null,
    val name: String,
    val currency: CurrencyEntity,
    val amountMoney: String? = null,
    val incomeMoney: String? = null,
    val consumptionMoney: String? = null,
) {
    companion object {
        val default = CreateWalletEntity(
            limit = "100000",
            name = "Кошелек 1",
            currency = CurrencyEntity.default
        )
    }
}

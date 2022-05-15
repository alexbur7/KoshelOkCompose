package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

data class CreateWalletEntity(
    val id: Long? = null,
    val limit: String? = null,
    val name: String,
    val currency: CurrencyEntity,
)

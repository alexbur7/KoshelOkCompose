package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.enums.Currency

data class CreateWalletEntity(
    val id: Long? = null,
    var limit: String? = null,
    var name: String,
    var currency: Currency,
)

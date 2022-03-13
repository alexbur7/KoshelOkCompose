package ru.alexbur.koshelok.domain.entities.wallet

import ru.alexbur.koshelok.domain.enums.Currency

data class WalletEntity(
    val id: Long,
    val name: String,
    val amountMoney: String,
    val currency: Currency,
    val isHide: Boolean
)

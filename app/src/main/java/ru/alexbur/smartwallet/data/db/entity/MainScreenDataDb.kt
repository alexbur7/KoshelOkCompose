package ru.alexbur.smartwallet.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MainScreenDataDb(
    @Embedded
    val balanceDb: BalanceDb,
    @Relation(
        parentColumn = "email",
        entityColumn = "email"
    )
    val exchangeRatesDb: ExchangeRatesDb,
    @Relation(
        parentColumn = "email",
        entityColumn = "email"
    )
    val wallets: List<WalletDb>
)

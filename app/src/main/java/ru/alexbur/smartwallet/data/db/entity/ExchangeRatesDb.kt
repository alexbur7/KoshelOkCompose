package ru.alexbur.smartwallet.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExchangeRates")
data class ExchangeRatesDb(
    @PrimaryKey
    val id: Long = 0,
    @Embedded
    val firstCurrency: CurrencyDb,
    @Embedded
    val secondCurrency: CurrencyDb,
    @Embedded
    val thirdCurrency: CurrencyDb
)

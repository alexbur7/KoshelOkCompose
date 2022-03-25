package ru.alexbur.smartwallet.data.db.entity

import androidx.annotation.FloatRange
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Wallets")
data class WalletDb(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val amountMoney: String,
    val income: String,
    val consumption: String,
    val limit: String?,
    val currency: String,
    val isHide: Boolean,
    @FloatRange(from = 0.0, to =1.0)
    val partSpending: Float?,
    val email: String
)

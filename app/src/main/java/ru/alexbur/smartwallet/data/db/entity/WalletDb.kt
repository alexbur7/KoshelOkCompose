package ru.alexbur.smartwallet.data.db.entity

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
    val isExceededLimit: Boolean,
    val isHide: Boolean,
    val email: String
)

package ru.alexbur.smartwallet.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transactions")
data class TransactionDb(
    @PrimaryKey
    val id: Long = 0,
    val money: String,
    val idCategory: Long,
    val type: Int,
    val operation: String,
    val idIcon: Int,
    val currency: String,
    val time: Long,
    val walletId: Long
)

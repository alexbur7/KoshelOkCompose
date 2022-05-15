package ru.alexbur.smartwallet.data.db.entity

import androidx.room.ColumnInfo

data class CurrencyDb(
    val currencyId: Long,
    @ColumnInfo(name = "currency_name")
    val name: String,
    val course: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "full_list_name")
    val fullListName: String,
    val icon: String,
    @ColumnInfo(name = "is_up")
    val isUp: Boolean
)
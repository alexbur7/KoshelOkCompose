package ru.alexbur.koshelok.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExchangeRates")
data class ExchangeRatesDb(
    @PrimaryKey
    val personId: Long = 0,
    val firstCurrency: String,
    val firstCourse: String,
    val firstIsUp: Boolean,
    val secondCurrency: String,
    val secondCourse: String,
    val secondIsUp: Boolean,
    val thirdCurrency: String,
    val thirdCourse: String,
    val thirdIsUp: Boolean
)

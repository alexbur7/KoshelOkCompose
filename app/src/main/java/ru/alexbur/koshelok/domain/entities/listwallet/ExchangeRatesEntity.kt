package ru.alexbur.koshelok.domain.entities.listwallet

import ru.alexbur.koshelok.domain.enums.Currency

data class ExchangeRatesEntity(
    val firstCurrency: Currency,
    val firstCourse: String,
    val firstIsUp: Boolean,
    val secondCurrency: Currency,
    val secondCourse: String,
    val secondIsUp: Boolean,
    val thirdCurrency: Currency,
    val thirdCourse: String,
    val thirdIsUp: Boolean
)
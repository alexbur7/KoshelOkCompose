package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyApi(
    val currencyId: Long,
    @SerialName("name")
    val name: String,
    val course: String,
    val fullName: String,
    val fullListName: String,
    val icon: String,
    val isUp: Boolean
)
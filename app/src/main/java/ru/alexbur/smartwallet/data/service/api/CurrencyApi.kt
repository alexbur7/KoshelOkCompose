package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyApi(
    @SerialName("name")
    val name: String,
    val course: String,
    val isUp: Boolean
)
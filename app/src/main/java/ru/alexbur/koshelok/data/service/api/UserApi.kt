package ru.alexbur.koshelok.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApi(
    @SerialName("email")
    val email: String
)
package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.Serializable

@Serializable
data class ResponseApi<T>(
    val result: T
)
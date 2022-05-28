package ru.alexbur.smartwallet.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryApi(
    @SerialName("category_id")
    val id: Long? = null,
    @SerialName("category_type")
    val type: Boolean? = null,
    @SerialName("name")
    val operation: String? = null,
    @SerialName("icon_id")
    val idIcon: Int? = null
)

package ru.alexbur.smartwallet.domain.entities.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryEntity(
    val id: Long,
    var type: TypeOperation,
    var operation: String,
    var iconId: Int,
    var color: Int
): Parcelable
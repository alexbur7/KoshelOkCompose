package ru.alexbur.smartwallet.domain.entities.utils

data class CategoryEntity(
    val id: Long,
    val type: TypeOperation,
    val operation: String,
    val iconId: Int,
) {
    val isIncome: Boolean
        get() = type == TypeOperation.SELECT_INCOME
}
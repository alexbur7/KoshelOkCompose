package ru.alexbur.smartwallet.domain.entities.utils

import ru.alexbur.smartwallet.R

data class CategoryEntity(
    val id: Long? = null,
    val type: TypeOperation,
    val operation: String,
    val iconId: Int,
) {
    val isIncome: Boolean
        get() = type == TypeOperation.SELECT_INCOME

    companion object{
        val defaultCategory = CategoryEntity(
            type = TypeOperation.SELECT_INCOME,
            operation = "",
            iconId = R.drawable.capitalization
        )
    }
}
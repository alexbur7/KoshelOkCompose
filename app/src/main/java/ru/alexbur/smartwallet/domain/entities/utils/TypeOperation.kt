package ru.alexbur.smartwallet.domain.entities.utils

import androidx.annotation.StringRes
import ru.alexbur.smartwallet.R

enum class TypeOperation(val code: Int,@StringRes val textId: Int) {
    SELECT_INCOME(0, R.string.replenishment),
    SELECT_EXPENSE(1, R.string.spending)
}

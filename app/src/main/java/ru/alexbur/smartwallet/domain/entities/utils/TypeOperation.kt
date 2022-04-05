package ru.alexbur.smartwallet.domain.entities.utils

import androidx.annotation.StringRes
import ru.alexbur.smartwallet.R

enum class TypeOperation(val code: Int, @StringRes val textId: Int, @StringRes val typeId: Int) {
    SELECT_INCOME(0, R.string.replenishment, R.string.income_text),
    SELECT_EXPENSE(1, R.string.spending, R.string.consumption_text)
}

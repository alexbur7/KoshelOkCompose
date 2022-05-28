package ru.alexbur.smartwallet.domain.enums

import androidx.annotation.StringRes
import ru.alexbur.smartwallet.R

enum class TypeOperation(val serverCode:Boolean, val code: Int, @StringRes val textId: Int, @StringRes val typeId: Int) {
    SELECT_INCOME(false,0, R.string.replenishment, R.string.income_text),
    SELECT_EXPENSE(true,1, R.string.spending, R.string.consumption_text);

    companion object {
        fun convertCodeToType(code: Int): TypeOperation {
            return values().toList().find { it.code == code } ?: throw NullPointerException()
        }
    }
}

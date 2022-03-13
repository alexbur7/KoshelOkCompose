package ru.alexbur.koshelok.data.mappers

import ru.alexbur.koshelok.domain.entities.utils.TypeOperation
import javax.inject.Inject

class IntToTypeCategoryMapper @Inject constructor() : (Int) -> TypeOperation {

    override operator fun invoke(type: Int): TypeOperation {
        return when (type) {
            0 -> TypeOperation.SELECT_INCOME
            1 -> TypeOperation.SELECT_EXPENSE
            else -> throw NullPointerException("error type")
        }
    }
}

package ru.alexbur.smartwallet.data.mappers

import ru.alexbur.smartwallet.domain.enums.TypeOperation
import javax.inject.Inject

class IntToTypeCategoryMapper @Inject constructor() : (Boolean?) -> TypeOperation {

    override operator fun invoke(type: Boolean?): TypeOperation {
        return when (type) {
            false -> TypeOperation.SELECT_INCOME
            true -> TypeOperation.SELECT_EXPENSE
            else -> TypeOperation.SELECT_INCOME
        }
    }
}

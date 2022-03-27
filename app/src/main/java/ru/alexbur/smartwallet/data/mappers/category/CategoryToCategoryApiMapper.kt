package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.data.service.api.CategoryApi
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import javax.inject.Inject

class CategoryToCategoryApiMapper @Inject constructor(
    private val iconConverter: IconConverter
) : (CategoryEntity) -> CategoryApi {

    override operator fun invoke(category: CategoryEntity) =
        CategoryApi(
            id = category.id,
            type = category.type.code,
            operation = category.operation,
            idIcon = iconConverter.convertDrawableIdToNumber(category.iconId)
        )
}

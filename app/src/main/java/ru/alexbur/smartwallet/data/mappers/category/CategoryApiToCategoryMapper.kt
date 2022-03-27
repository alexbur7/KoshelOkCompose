package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.mappers.IntToTypeCategoryMapper
import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.data.service.api.CategoryApi
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import javax.inject.Inject

class CategoryApiToCategoryMapper @Inject constructor(
    private val intToTypeCategoryMapper: IntToTypeCategoryMapper,
    private val iconConverter: IconConverter
) : (CategoryApi) -> CategoryEntity {
    override operator fun invoke(categoryApi: CategoryApi) =
        CategoryEntity(
            id = categoryApi.id ?: 0,
            type = intToTypeCategoryMapper(categoryApi.type),
            operation = categoryApi.operation,
            iconId = iconConverter.convertNumberToDrawableId(categoryApi.idIcon)
        )
}

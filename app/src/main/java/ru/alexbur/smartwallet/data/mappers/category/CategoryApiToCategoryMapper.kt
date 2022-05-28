package ru.alexbur.smartwallet.data.mappers.category

import ru.alexbur.smartwallet.data.mappers.IntToTypeCategoryMapper
import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.data.service.api.CategoryApi
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import javax.inject.Inject

class CategoryApiToCategoryMapper @Inject constructor(
    private val intToTypeCategoryMapper: IntToTypeCategoryMapper,
    private val iconConverter: IconConverter
) : (ResponseApi<CategoryApi>) -> CategoryEntity {
    override operator fun invoke(categoryApi: ResponseApi<CategoryApi>) =
        CategoryEntity(
            id = categoryApi.result.id ?: 0,
            type = intToTypeCategoryMapper(categoryApi.result.type),
            operation = categoryApi.result.operation.orEmpty(),
            iconId = iconConverter.convertNumberToDrawableId(categoryApi.result.idIcon)
        )
}

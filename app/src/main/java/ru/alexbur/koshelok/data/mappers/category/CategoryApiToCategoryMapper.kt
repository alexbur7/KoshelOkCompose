package ru.alexbur.koshelok.data.mappers.category

import ru.alexbur.koshelok.data.mappers.IntToTypeCategoryMapper
import ru.alexbur.koshelok.data.utils.IconConverter
import ru.alexbur.koshelok.data.service.api.CategoryApi
import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity
import javax.inject.Inject

class CategoryApiToCategoryMapper @Inject constructor(
    private val intToTypeCategoryMapper: IntToTypeCategoryMapper,
    private val iconConverter: IconConverter
) : (CategoryApi) -> CategoryEntity {
    override operator fun invoke(categoryApi: CategoryApi) =
        CategoryEntity(
            id = categoryApi.id,
            type = intToTypeCategoryMapper(categoryApi.type),
            operation = categoryApi.operation,
            iconId = iconConverter.convertNumberToDrawableId(categoryApi.idIcon),
            color = categoryApi.color
        )
}

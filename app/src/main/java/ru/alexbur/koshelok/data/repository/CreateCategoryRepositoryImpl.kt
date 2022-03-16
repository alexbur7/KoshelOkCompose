package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.koshelok.data.mappers.category.CategoryToCategoryApiMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity
import ru.alexbur.koshelok.domain.repositories.CreateCategoryRepository
import javax.inject.Inject

class CreateCategoryRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val categoryMapper: CategoryToCategoryApiMapper,
    private val categoryApiMapper: CategoryApiToCategoryMapper
) : CreateCategoryRepository {

    override suspend fun createCategory(category: CategoryEntity): Result<CategoryEntity> {
        return appService.createCategory(
            categoryMapper(category)
        ).map(categoryApiMapper)
    }
}

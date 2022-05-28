package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.smartwallet.data.mappers.category.CategoryToCategoryApiMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.repositories.CreateCategoryRepository
import javax.inject.Inject

class CreateCategoryRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val categoryMapper: CategoryToCategoryApiMapper,
    private val categoryApiMapper: CategoryApiToCategoryMapper
) : CreateCategoryRepository {

    override suspend fun createCategory(category: CategoryEntity): Result<CategoryEntity> {
        return runCatching {
            appService.createCategory(
                categoryMapper(category)
            )
        }.map(categoryApiMapper)
    }
}

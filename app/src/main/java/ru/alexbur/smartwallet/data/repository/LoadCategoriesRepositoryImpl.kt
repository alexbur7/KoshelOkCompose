package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.repositories.LoadCategoriesRepository
import javax.inject.Inject

class LoadCategoriesRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val categoryMapper: CategoryApiToCategoryMapper
) : LoadCategoriesRepository {

    override suspend fun getCategories(): Result<List<CategoryEntity>> {
        return resultRequest { appService.getCategories() }
            .map { categories ->
                categories.map(categoryMapper)
            }
    }
}

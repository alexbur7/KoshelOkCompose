package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity
import ru.alexbur.koshelok.domain.repositories.LoadCategoriesRepository
import javax.inject.Inject

class LoadCategoriesRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val categoryMapper: CategoryApiToCategoryMapper
) : LoadCategoriesRepository {

    override suspend fun getCategories(personId: Long, type: Int): Result<List<CategoryEntity>> {
        return appService.getCategories(personId, type)
            .map { categories ->
                categories.map(categoryMapper)
            }
    }
}

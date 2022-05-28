package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.update
import ru.alexbur.smartwallet.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.smartwallet.data.mappers.category.CategoryToCategoryApiMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.repositories.CreateCategoryRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

class CreateCategoryRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val categoryMapper: CategoryToCategoryApiMapper,
    private val categoryApiMapper: CategoryApiToCategoryMapper,
    private val savingDataManager: SavingDataManager
) : CreateCategoryRepository {

    override suspend fun createCategory(category: CategoryEntity): Result<CategoryEntity> {
        return runCatching {
            appService.createCategory(
                categoryMapper(category)
            )
        }.map(categoryApiMapper).onSuccess {
            savingDataManager.categoriesFlow.update { list ->
                list + it
            }
            savingDataManager.createCategoryFlow.emit(CategoryEntity.defaultCategory)
        }
    }
}

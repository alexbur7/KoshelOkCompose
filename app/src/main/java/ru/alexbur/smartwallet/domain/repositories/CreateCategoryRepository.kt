package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity

interface CreateCategoryRepository {

    suspend fun createCategory(category: CategoryEntity): Result<CategoryEntity>
}
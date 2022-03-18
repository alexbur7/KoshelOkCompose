package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity

interface LoadCategoriesRepository {

    suspend fun getCategories(type: Int): Result<List<CategoryEntity>>
}
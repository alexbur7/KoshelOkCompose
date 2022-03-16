package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity

interface CreateCategoryRepository {

    suspend fun createCategory(category: CategoryEntity): Result<CategoryEntity>
}
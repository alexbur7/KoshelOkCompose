package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity

interface CreateCategoryRepository {

    suspend fun createCategory(personId: Long, category: CategoryEntity): Result<Boolean>
}
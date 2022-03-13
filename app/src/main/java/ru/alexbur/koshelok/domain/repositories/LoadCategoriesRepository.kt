package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity

interface LoadCategoriesRepository {

    suspend fun getCategories(personId: Long, type: Int): Result<List<CategoryEntity>>
}
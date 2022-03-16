package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity

interface LoadCategoriesRepository {

    suspend fun getCategories(type: Int): Result<List<CategoryEntity>>
}
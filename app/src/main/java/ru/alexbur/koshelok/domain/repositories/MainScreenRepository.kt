package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.listwallet.MainScreenDataEntity

interface MainScreenRepository {

    suspend fun getServerMainScreenData(personId: Long): Result<MainScreenDataEntity>

    suspend fun getDbMainScreenData(personId: Long): Result<MainScreenDataEntity>
}
package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity

interface MainScreenRepository {

    suspend fun getServerMainScreenData(): Result<MainScreenDataEntity>

    suspend fun getDbMainScreenData(): Result<MainScreenDataEntity>
}
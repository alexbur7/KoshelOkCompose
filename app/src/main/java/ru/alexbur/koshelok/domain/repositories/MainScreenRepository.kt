package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.listwallet.MainScreenDataEntity

interface MainScreenRepository {

    suspend fun getServerMainScreenData(): Result<MainScreenDataEntity>

    suspend fun getDbMainScreenData(): Result<MainScreenDataEntity>
}
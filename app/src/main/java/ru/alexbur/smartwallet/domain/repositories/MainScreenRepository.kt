package ru.alexbur.smartwallet.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity

interface MainScreenRepository {

    val nameFlow: Flow<String?>

    suspend fun getServerMainScreenData(): Result<MainScreenDataEntity>

    suspend fun getDbMainScreenData(): Result<MainScreenDataEntity>
}
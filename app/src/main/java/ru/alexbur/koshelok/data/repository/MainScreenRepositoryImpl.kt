package ru.alexbur.koshelok.data.repository

import kotlinx.coroutines.flow.firstOrNull
import ru.alexbur.koshelok.data.db.source.MainWalletSource
import ru.alexbur.koshelok.data.mappers.MainScreenDataApiToEntityMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.data.utils.AccountDataStore
import ru.alexbur.koshelok.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.koshelok.domain.repositories.MainScreenRepository
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mapper: MainScreenDataApiToEntityMapper,
    private val appService: AppService,
    private val mainWalletSource: MainWalletSource,
    private val accountDataStore: AccountDataStore
) : MainScreenRepository {

    override suspend fun getServerMainScreenData(): Result<MainScreenDataEntity> {
        return appService.getDataForMainScreen()
            .onSuccess { mainData ->
                accountDataStore.email.collect {
                    if (it != null) {
                        mainWalletSource.insertMainScreenData(it, mainData)
                    }
                }
            }
            .map(mapper)
    }

    override suspend fun getDbMainScreenData(): Result<MainScreenDataEntity> {
        val email = accountDataStore.email.firstOrNull() ?: return Result.failure(Exception())
        return mainWalletSource.getMainScreenData(email = email)
            .map(mapper)
    }
}

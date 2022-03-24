package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import ru.alexbur.smartwallet.data.db.source.MainWalletSource
import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.MainScreenDataApiToEntityMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.data.utils.AccountDataStore
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.repositories.MainScreenRepository
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mapper: MainScreenDataApiToEntityMapper,
    private val appService: AppService,
    private val mainWalletSource: MainWalletSource,
    private val accountDataStore: AccountDataStore
) : MainScreenRepository {
    override val nameFlow: Flow<String?>
        get() = accountDataStore.name

    override suspend fun getServerMainScreenData(): Result<MainScreenDataEntity> {
        return resultRequest { appService.getDataForMainScreen() }.onSuccess { data ->
            accountDataStore.email.firstOrNull()?.let {
                mainWalletSource.insertMainScreenData(it, data)
            }
        }.map(mapper)
    }

    override suspend fun getDbMainScreenData(): Result<MainScreenDataEntity> {
        val email = accountDataStore.email.firstOrNull() ?: return Result.failure(Exception())
        return mainWalletSource.getMainScreenData(email = email)
            .map(mapper)
    }
}

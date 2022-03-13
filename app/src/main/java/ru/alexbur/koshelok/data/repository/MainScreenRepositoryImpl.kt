package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.db.source.MainWalletSource
import ru.alexbur.koshelok.data.mappers.MainScreenDataApiToEntityMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.koshelok.domain.repositories.MainScreenRepository
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val mapper: MainScreenDataApiToEntityMapper,
    private val appService: AppService,
    private val mainWalletSource: MainWalletSource,
) : MainScreenRepository {

    override suspend fun getServerMainScreenData(personId: Long): Result<MainScreenDataEntity> {
        return appService.getDataForMainScreen(personId)
            .onSuccess {
                mainWalletSource.insertMainScreenData(personId, it)
            }
            .map(mapper)
    }

    override suspend fun getDbMainScreenData(personId: Long): Result<MainScreenDataEntity> {
        return mainWalletSource.getMainScreenData(personId)
            .map(mapper)
    }
}

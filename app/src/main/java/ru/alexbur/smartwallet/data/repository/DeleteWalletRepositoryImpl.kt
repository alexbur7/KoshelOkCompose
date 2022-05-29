package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.mappers.MainScreenDataApiToEntityMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.repositories.DeleteWalletRepository
import javax.inject.Inject

class DeleteWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val walletSource: WalletSource,
    private val mapper: MainScreenDataApiToEntityMapper
) : DeleteWalletRepository {

    override suspend fun deleteWallet(walletId: Long): Result<MainScreenDataEntity> {
        return runCatching { appService.deleteWallet(walletId) }.onSuccess {
            walletSource.deleteWallet(walletId)
        }.map(mapper)
    }
}

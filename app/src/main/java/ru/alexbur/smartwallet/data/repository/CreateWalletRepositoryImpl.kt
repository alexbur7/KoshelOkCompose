package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.mappers.category.CreateWalletEntityToApiMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToWalletEntityMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.repositories.CreateWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

class CreateWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val mapperWallet: CreateWalletEntityToApiMapper,
    private val walletEntityMapper: WalletApiToWalletEntityMapper,
    private val savingDataManager: SavingDataManager,
    private val walletSource: WalletSource
) : CreateWalletRepository {
    override suspend fun createWallet(
        createWallet: CreateWalletEntity
    ): Result<WalletEntity> {
        return runCatching {
            appService.createWallet(mapperWallet(createWallet))
        }.onSuccess { wallet ->
            walletSource.insertWallet(wallet.result)
        }.map(walletEntityMapper).onSuccess {
            savingDataManager.walletIdFlow.emit(it.id)
            savingDataManager.walletFlow.emit(it)
            savingDataManager.createWalletFlow.emit(CreateWalletEntity.default)
        }
    }

    override suspend fun editWallet(
        walletEntity: CreateWalletEntity
    ): Result<WalletEntity> {
        return runCatching {
            appService.editWallet(
                walletEntity.id ?: 0,
                mapperWallet(walletEntity)
            )
        }.onSuccess { wallet ->
            walletSource.insertWallet(wallet.result)
        }.map(walletEntityMapper).onSuccess {
            savingDataManager.walletIdFlow.emit(it.id)
            savingDataManager.walletFlow.emit(it)
            savingDataManager.editWalletFlow.emit(null)
            savingDataManager.createWalletFlow.emit(CreateWalletEntity.default)
        }
    }
}

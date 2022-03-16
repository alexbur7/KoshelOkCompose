package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.db.source.WalletSource
import ru.alexbur.koshelok.data.mappers.category.CreateWalletEntityToWalletApiMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToWalletEntityMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.koshelok.domain.entities.wallet.WalletEntity
import ru.alexbur.koshelok.domain.repositories.CreateWalletRepository
import javax.inject.Inject

class CreateWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val mapperWallet: CreateWalletEntityToWalletApiMapper,
    private val walletEntityMapper: WalletApiToWalletEntityMapper,
    private val walletSource: WalletSource
) : CreateWalletRepository {
    override suspend fun createWallet(
        createWallet: CreateWalletEntity
    ): Result<WalletEntity> {
        return appService.createWallet(mapperWallet(createWallet)).onSuccess { wallet ->
            walletSource.insertWallet(wallet)
        }.map(walletEntityMapper)
    }
}

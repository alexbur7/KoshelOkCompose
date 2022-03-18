package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

interface CreateWalletRepository {

    suspend fun createWallet(
        createWallet: CreateWalletEntity
    ): Result<WalletEntity>
}
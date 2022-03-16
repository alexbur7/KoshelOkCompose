package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.koshelok.domain.entities.wallet.WalletEntity

interface CreateWalletRepository {

    suspend fun createWallet(
        createWallet: CreateWalletEntity
    ): Result<WalletEntity>
}
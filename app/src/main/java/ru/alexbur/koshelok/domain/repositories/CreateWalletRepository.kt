package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.wallet.CreateWalletEntity

interface CreateWalletRepository {

    suspend fun createWallet(
        personId: Long,
        createWallet: CreateWalletEntity
    ): Result<Long>
}
package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity

interface DeleteWalletRepository {

    suspend fun deleteWallet(walletId: Long): Result<MainScreenDataEntity>
}
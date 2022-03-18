package ru.alexbur.smartwallet.domain.repositories

interface DeleteWalletRepository {

    suspend fun deleteWallet(walletId: Long): Result<Boolean>
}
package ru.alexbur.koshelok.domain.repositories

interface DeleteWalletRepository {

    suspend fun deleteWallet(walletId: Long): Result<Boolean>
}
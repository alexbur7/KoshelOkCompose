package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem

interface DetailWalletRepository {

    suspend fun getServerDetailWalletData(walletId: Long): Result<List<DetailWalletItem>>

    suspend fun getDbDetailWalletData(walletId: Long): Result<List<DetailWalletItem>>
}
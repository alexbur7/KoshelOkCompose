package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

interface DetailWalletRepository {

    suspend fun getServerWalletsData(): Result<List<WalletEntity>>

    suspend fun getServerTransactionsData(walletId: Long): Result<List<DetailWalletItem>>

    suspend fun getDbWalletData(): Result<List<WalletEntity>>

    suspend fun getDbTransactionsData(walletId: Long): Result<List<DetailWalletItem>>
}
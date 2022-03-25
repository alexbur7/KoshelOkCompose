package ru.alexbur.smartwallet.data.db.source

import kotlinx.coroutines.flow.firstOrNull
import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToDbMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletDbToEntityMapper
import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.data.utils.AccountDataStore
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import javax.inject.Inject

interface WalletSource {

    suspend fun insertWallet(wallet: WalletApi)

    suspend fun insertWallets(wallets: List<WalletApi>)

    suspend fun getWallets(): Result<List<WalletEntity>>

    suspend fun deleteWallet(walletId: Long)
}

class WalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val walletMapper: WalletApiToDbMapper,
    private val walletDbMapper: WalletDbToEntityMapper,
    private val accountDataStore: AccountDataStore
) : WalletSource {

    override suspend fun insertWallet(wallet: WalletApi) {
        val email = accountDataStore.email.firstOrNull() ?: return
        database.getWalletsDao()
            .addWallet(walletMapper(email = email, wallet = wallet))
    }

    override suspend fun insertWallets(wallets: List<WalletApi>) {
        val email = accountDataStore.email.firstOrNull() ?: return
        database.getWalletsDao()
            .addWallets(wallets.map { walletMapper(email = email, wallet = it) })
    }

    override suspend fun getWallets(): Result<List<WalletEntity>> {
        val email = accountDataStore.email.firstOrNull() ?: return Result.success(emptyList())
        val data = database.getWalletsDao().getWallets(email)
        val resultDb = if (data.isNotEmpty()) {
            Result.success(data)
        } else {
            Result.failure(exception = Throwable("MainScreenData is null"))
        }
        return resultDb
            .map {
                it.map(walletDbMapper)
            }
    }

    override suspend fun deleteWallet(walletId: Long) {
        return database.getWalletsDao().deleteWallet(walletId)
    }
}

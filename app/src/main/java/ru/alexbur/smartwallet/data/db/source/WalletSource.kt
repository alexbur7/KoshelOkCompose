package ru.alexbur.smartwallet.data.db.source

import kotlinx.coroutines.flow.firstOrNull
import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToDbMapper
import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.data.utils.AccountDataStore
import javax.inject.Inject

interface WalletSource {

    suspend fun insertWallet(wallet: WalletApi)

    suspend fun deleteWallet(walletId: Long)
}

class WalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val walletMapper: WalletApiToDbMapper,
    private val accountDataStore: AccountDataStore
) : WalletSource {

    override suspend fun insertWallet(wallet: WalletApi) {
        val email = accountDataStore.email.firstOrNull()
        check(email != null)
        database.getWalletsDao()
            .addWallet(walletMapper(email = email, wallet = wallet))
    }

    override suspend fun deleteWallet(walletId: Long) {
        return database.getWalletsDao().deleteWallet(walletId)
    }
}

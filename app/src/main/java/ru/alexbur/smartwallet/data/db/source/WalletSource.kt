package ru.alexbur.smartwallet.data.db.source

import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToWalletDbMapper
import ru.alexbur.smartwallet.data.service.api.WalletApi
import javax.inject.Inject

interface WalletSource {

    suspend fun insertWallet(wallet: WalletApi)

    suspend fun deleteWallet(walletId: Long)
}

class WalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val walletMapper: WalletApiToWalletDbMapper
) : WalletSource {

    override suspend fun insertWallet(wallet: WalletApi) {
        database.getWalletsDao()
            .addWallet(walletMapper(wallet))
    }

    override suspend fun deleteWallet(walletId: Long) {
        return database.getWalletsDao().deleteWallet(walletId)
    }
}

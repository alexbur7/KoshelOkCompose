package ru.alexbur.koshelok.data.db.source

import ru.alexbur.koshelok.data.db.KoshelokDatabase
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToWalletDbMapper
import ru.alexbur.koshelok.data.service.api.WalletApi
import javax.inject.Inject

interface WalletSource {

    suspend fun insertWallet(wallet: WalletApi)

    suspend fun deleteWallet(walletId: Long)
}

class WalletSourceImpl @Inject constructor(
    private val database: KoshelokDatabase,
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

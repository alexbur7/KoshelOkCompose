package ru.alexbur.smartwallet.data.db.source

import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.DetailWalletDbToApiMapper
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionsApiToTransactionsDbMapper
import ru.alexbur.smartwallet.data.service.api.DetailWalletApi
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

interface DetailWalletSource {

    suspend fun insertAllTransactions(wallets: List<TransactionApi>, walletId: Long)

    suspend fun getDetailWallet(walletId: Long): Result<DetailWalletApi>

    suspend fun deleteTransaction(transactionId: Long)
}

class DetailWalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val transactionsDbMapper: TransactionsApiToTransactionsDbMapper,
    private val detailMapper: DetailWalletDbToApiMapper
) : DetailWalletSource {


    override suspend fun insertAllTransactions(wallets: List<TransactionApi>, walletId: Long) {
        return database.getTransactionDao()
            .insertAllTransactions(
                wallets.map {
                    transactionsDbMapper(it, walletId)
                }
            )
    }

    override suspend fun getDetailWallet(walletId: Long): Result<DetailWalletApi> {
        val walletDb = database.getWalletsDao().getDetailWalletDb(walletId)
        val resultDb = if (walletDb != null) {
            Result.success(walletDb)
        } else {
            Result.failure(exception = Throwable("Wallet is null"))
        }
        return resultDb
            .map(detailMapper)
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        database.getTransactionDao().deleteTransactions(transactionId)
    }
}

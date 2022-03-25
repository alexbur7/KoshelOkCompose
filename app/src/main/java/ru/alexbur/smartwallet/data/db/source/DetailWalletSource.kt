package ru.alexbur.smartwallet.data.db.source

import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionDbToTransactionApiMapper
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionsApiToTransactionsDbMapper
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

interface DetailWalletSource {

    suspend fun insertAllTransactions(wallets: List<TransactionApi>, walletId: Long)

    suspend fun deleteTransaction(transactionId: Long)

    suspend fun getAllTransaction(walletId: Long): Result<List<TransactionApi>>
}

class DetailWalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val transactionsDbMapper: TransactionsApiToTransactionsDbMapper,
    private val transactionApiMapper: TransactionDbToTransactionApiMapper
) : DetailWalletSource {


    override suspend fun insertAllTransactions(wallets: List<TransactionApi>, walletId: Long) {
        database.getTransactionDao()
            .insertAllTransactions(
                wallets.map {
                    transactionsDbMapper(it, walletId)
                }
            )
    }

    override suspend fun deleteTransaction(transactionId: Long) {
        database.getTransactionDao().deleteTransactions(transactionId)
    }

    override suspend fun getAllTransaction(walletId: Long): Result<List<TransactionApi>> {
        val data = database.getTransactionDao().getTransactionsByWalletId(walletId)
        val result = if (data.isNotEmpty()) {
            Result.success(data)
        } else {
            Result.failure(Throwable("Transactions is empty"))
        }
        return result.map {
            it.map(transactionApiMapper)
        }
    }
}

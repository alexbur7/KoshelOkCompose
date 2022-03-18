package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity

interface ActionTransactionRepository {

    suspend fun editTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction>

    suspend fun createTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction>
}
package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem
import ru.alexbur.koshelok.domain.entities.wallet.TransactionEntity

interface ActionTransactionRepository {

    suspend fun editTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction>

    suspend fun createTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction>
}
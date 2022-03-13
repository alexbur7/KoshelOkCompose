package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.wallet.TransactionEntity

interface ActionTransactionRepository {

    suspend fun editTransaction(transactionEntity: TransactionEntity): Result<Boolean>

    suspend fun createTransaction(transactionEntity: TransactionEntity): Result<Boolean>
}
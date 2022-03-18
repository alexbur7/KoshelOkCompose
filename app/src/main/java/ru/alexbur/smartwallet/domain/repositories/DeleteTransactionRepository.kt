package ru.alexbur.smartwallet.domain.repositories

interface DeleteTransactionRepository {

    suspend fun deleteTransaction(transactionId: Long): Result<Boolean>
}
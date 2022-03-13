package ru.alexbur.koshelok.domain.repositories

interface DeleteTransactionRepository {

    suspend fun deleteTransaction(transactionId: Long): Result<Boolean>
}
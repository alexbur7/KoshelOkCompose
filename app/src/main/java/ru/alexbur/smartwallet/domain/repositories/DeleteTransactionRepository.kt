package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

interface DeleteTransactionRepository {

    suspend fun deleteTransaction(transactionId: Long): Result<WalletEntity>
}
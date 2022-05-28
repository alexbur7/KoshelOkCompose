package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.db.source.DetailWalletSource
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import javax.inject.Inject

class DeleteTransactionRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val detailSource: DetailWalletSource
) : DeleteTransactionRepository {

    override suspend fun deleteTransaction(transactionId: Long): Result<Boolean> {
        return runCatching { appService.deleteTransaction(transactionId) }.onSuccess {
            detailSource.deleteTransaction(transactionId)
        }.map { it.result }
    }
}

package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.db.source.DetailWalletSource
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.repositories.DeleteTransactionRepository
import javax.inject.Inject

class DeleteTransactionRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val detailSource: DetailWalletSource
) : DeleteTransactionRepository {

    override suspend fun deleteTransaction(transactionId: Long): Result<Boolean> {
        return appService.deleteTransaction(transactionId).onSuccess {
            detailSource.deleteTransaction(transactionId)
        }
    }
}

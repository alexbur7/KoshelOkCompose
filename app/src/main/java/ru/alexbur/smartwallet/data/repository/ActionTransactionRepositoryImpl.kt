package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionToTransactionApiMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.repositories.ActionTransactionRepository
import javax.inject.Inject

class ActionTransactionRepositoryImpl @Inject constructor(
    private val mapper: TransactionToTransactionApiMapper,
    private val mapperTransaction: TransactionApiToDetailWalletTransactionMapper,
    private val appService: AppService
) : ActionTransactionRepository {

    override suspend fun editTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return resultRequest {
            appService.editTransaction(
                transactionEntity.id ?: 0,
                mapper(transactionEntity)
            )
        }.map(mapperTransaction)
    }

    override suspend fun createTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return resultRequest { appService.createTransaction(mapper(transactionEntity)) }.map(
            mapperTransaction
        )
    }
}

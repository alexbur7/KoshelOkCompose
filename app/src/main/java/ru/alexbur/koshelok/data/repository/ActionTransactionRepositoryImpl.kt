package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.koshelok.data.mappers.transactions.TransactionToTransactionApiMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.data.service.api.TransactionApi
import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem
import ru.alexbur.koshelok.domain.entities.wallet.TransactionEntity
import ru.alexbur.koshelok.domain.repositories.ActionTransactionRepository
import javax.inject.Inject

class ActionTransactionRepositoryImpl @Inject constructor(
    private val mapper: TransactionToTransactionApiMapper,
    private val mapperTransaction: TransactionApiToDetailWalletTransactionMapper,
    private val appService: AppService
) : ActionTransactionRepository {

    override suspend fun editTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return appService.editTransaction(transactionEntity.id ?: 0, mapper(transactionEntity)).map(mapperTransaction)
    }

    override suspend fun createTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return appService.createTransaction(mapper(transactionEntity)).map(mapperTransaction)
    }
}

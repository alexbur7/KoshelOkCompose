package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionToTransactionApiMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.repositories.CreateTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Inject

class CreateTransactionRepositoryImpl @Inject constructor(
    private val mapper: TransactionToTransactionApiMapper,
    private val mapperTransaction: TransactionApiToDetailWalletTransactionMapper,
    private val savingDataManager: SavingDataManager,
    private val appService: AppService
) : CreateTransactionRepository {

    override suspend fun editTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return runCatching {
            appService.editTransaction(
                transactionEntity.id ?: 0,
                mapper(transactionEntity)
            )
        }.map(mapperTransaction).onSuccess {
            savingDataManager.transactionFlow.emit(it)
            savingDataManager.editTransactionFLow.emit(null)
            savingDataManager.createTransactionFlow.emit(null)
        }
    }

    override suspend fun createTransaction(transactionEntity: TransactionEntity): Result<DetailWalletItem.Transaction> {
        return runCatching {
            appService.createTransaction(mapper(transactionEntity))
        }.map{
            mapperTransaction(it)
        }.onSuccess {
            savingDataManager.transactionFlow.emit(it)
            savingDataManager.createTransactionFlow.emit(null)
        }
    }
}

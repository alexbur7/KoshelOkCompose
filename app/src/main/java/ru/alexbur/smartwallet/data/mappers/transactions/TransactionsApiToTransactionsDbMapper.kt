package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.db.entity.TransactionDb
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToDbMapper
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

class TransactionsApiToTransactionsDbMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToDbMapper
) : (TransactionApi, Long) -> TransactionDb {
    override operator fun invoke(transaction: TransactionApi, walletId: Long): TransactionDb {
        return TransactionDb(
            id = transaction.id,
            money = transaction.money,
            idCategory = transaction.category.id ?: 0,
            type = transaction.category.type ?: true,
            operation = transaction.category.operation.orEmpty(),
            idIcon = transaction.category.idIcon ?: 0,
            currency = currencyMapper(transaction.currency),
            time = transaction.time,
            walletId = walletId
        )
    }
}

package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.db.entity.TransactionDb
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

class TransactionsApiToTransactionsDbMapper @Inject constructor() :
        (TransactionApi, Long) -> TransactionDb {
    override operator fun invoke(transaction: TransactionApi, walletId: Long): TransactionDb {
        return TransactionDb(
            id = transaction.id,
            money = transaction.money,
            idCategory = transaction.idCategory,
            type = transaction.type,
            operation = transaction.operation,
            idIcon = transaction.idIcon,
            currency = transaction.currency,
            time = transaction.time,
            walletId = walletId
        )
    }
}

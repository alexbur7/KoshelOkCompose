package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.db.entity.TransactionDb
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

class TransactionDbToTransactionApiMapper @Inject constructor() :
        (TransactionDb) -> TransactionApi {

    override fun invoke(transaction: TransactionDb): TransactionApi {
        return TransactionApi(
            id = transaction.id,
            money = transaction.money,
            idCategory = transaction.idCategory,
            type = transaction.type,
            operation = transaction.operation,
            idIcon = transaction.idIcon,
            currency = transaction.currency,
            time = transaction.time
        )
    }
}

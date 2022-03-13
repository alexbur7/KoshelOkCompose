package ru.alexbur.koshelok.data.mappers.transactions

import ru.alexbur.koshelok.data.db.entity.TransactionDb
import ru.alexbur.koshelok.data.service.api.TransactionApi
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
            color = transaction.color,
            currency = transaction.currency,
            time = transaction.time
        )
    }
}

package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.db.entity.TransactionDb
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyDbToApiMapper
import ru.alexbur.smartwallet.data.service.api.CategoryApi
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import javax.inject.Inject

class TransactionDbToTransactionApiMapper @Inject constructor(
    private val currencyMapper: CurrencyDbToApiMapper
) :
        (TransactionDb) -> TransactionApi {

    override fun invoke(transaction: TransactionDb): TransactionApi {
        return TransactionApi(
            id = transaction.id,
            money = transaction.money,
            category = CategoryApi(
                transaction.idCategory,
                transaction.type,
                transaction.operation,
                transaction.idIcon
            ),
            currency = currencyMapper(transaction.currency),
            time = transaction.time
        )
    }
}

package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.service.api.CreateTransactionApi
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import java.util.*
import javax.inject.Inject

class TransactionToTransactionApiMapper @Inject constructor() :
        (TransactionEntity) -> CreateTransactionApi {

    override operator fun invoke(transactionEntity: TransactionEntity) =
        CreateTransactionApi(
            id = transactionEntity.id,
            idWallet = transactionEntity.idWallet,
            money = transactionEntity.sum ?: "0",
            idCategory = transactionEntity.categoryEntity?.id ?: 0,
            time = transactionEntity.date ?: Calendar.getInstance().timeInMillis,
            currency = transactionEntity.currency.name
        )
}

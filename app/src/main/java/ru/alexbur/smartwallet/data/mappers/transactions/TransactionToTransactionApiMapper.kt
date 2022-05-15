package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.service.api.CreateTransactionApi
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import javax.inject.Inject

class TransactionToTransactionApiMapper @Inject constructor() :
        (TransactionEntity) -> CreateTransactionApi {

    override operator fun invoke(transactionEntity: TransactionEntity) =
        CreateTransactionApi(
            idWallet = transactionEntity.idWallet,
            money = transactionEntity.sum,
            idCategory = transactionEntity.categoryEntity.id ?: 0,
            time = transactionEntity.date,
            currencyId = transactionEntity.currency.id ?: 0
        )
}

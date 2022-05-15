package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.extentions.getFormattedDate
import ru.alexbur.smartwallet.data.extentions.getTime
import ru.alexbur.smartwallet.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import javax.inject.Inject

class TransactionApiToDetailWalletTransactionMapper @Inject constructor(
    private val mapperCategory: CategoryApiToCategoryMapper,
    private val mapperCurrency: CurrencyApiToEntityMapper
) : (TransactionApi) -> DetailWalletItem.Transaction {

    override operator fun invoke(transaction: TransactionApi) =
        DetailWalletItem.Transaction(
            id = transaction.id,
            category = mapperCategory(transaction.category),
            money = transaction.money,
            time = transaction.time.getTime(),
            day = transaction.time.getFormattedDate(),
            currency = mapperCurrency(transaction.currency)
        )
}

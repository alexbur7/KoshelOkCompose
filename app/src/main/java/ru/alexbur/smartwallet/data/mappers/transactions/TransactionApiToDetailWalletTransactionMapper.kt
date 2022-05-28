package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.extentions.getFormattedDate
import ru.alexbur.smartwallet.data.extentions.getTime
import ru.alexbur.smartwallet.data.mappers.category.CategoryApiToCategoryMapper
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import javax.inject.Inject

class TransactionApiToDetailWalletTransactionMapper @Inject constructor(
    private val mapperCategory: CategoryApiToCategoryMapper,
    private val mapperCurrency: CurrencyApiToEntityMapper
) : (ResponseApi<TransactionApi>) -> DetailWalletItem.Transaction {

    override operator fun invoke(transaction: ResponseApi<TransactionApi>) =
        DetailWalletItem.Transaction(
            id = transaction.result.id,
            category = mapperCategory(ResponseApi(transaction.result.category)),
            money = transaction.result.money,
            time = transaction.result.time.getTime(),
            day = transaction.result.time.getFormattedDate(),
            currency = mapperCurrency(ResponseApi(transaction.result.currency))
        )
}

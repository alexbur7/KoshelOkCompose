package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.extentions.defaultMoney
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
            id = transaction.result.id ?: 0,
            category = mapperCategory(ResponseApi(transaction.result.category)),
            money = transaction.result.money.defaultMoney(),
            time = transaction.result.time.getTime(),
            day = transaction.result.time.getFormattedDate(),
            currency = mapperCurrency(ResponseApi(transaction.result.currency)),
            walletId = transaction.result.walletId ?: 0,
            timeStamp = transaction.result.time
        )
}

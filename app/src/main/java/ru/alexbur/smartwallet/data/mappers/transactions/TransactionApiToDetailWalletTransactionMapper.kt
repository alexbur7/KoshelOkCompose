package ru.alexbur.smartwallet.data.mappers.transactions

import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.data.extentions.getFormattedDate
import ru.alexbur.smartwallet.data.extentions.getTime
import ru.alexbur.smartwallet.data.mappers.IntToTypeCategoryMapper
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.enums.Currency
import javax.inject.Inject

class TransactionApiToDetailWalletTransactionMapper @Inject constructor(
    private val intToTypeCategoryMapper: IntToTypeCategoryMapper,
    private val iconConverter: IconConverter
) : (TransactionApi) -> DetailWalletItem.Transaction {

    override operator fun invoke(transaction: TransactionApi) =
        DetailWalletItem.Transaction(
            id = transaction.id,
            category = CategoryEntity(
                id = transaction.idCategory,
                type = intToTypeCategoryMapper(transaction.type),
                operation = transaction.operation,
                iconId = iconConverter.convertNumberToDrawableId(transaction.idIcon),
                color = transaction.color
            ),
            money = transaction.money,
            time = transaction.time.getTime(),
            day = transaction.time.getFormattedDate(),
            currency = Currency.valueOf(transaction.currency)
        )
}

package ru.alexbur.koshelok.data.mappers.transactions

import ru.alexbur.koshelok.data.utils.IconConverter
import ru.alexbur.koshelok.data.extentions.getFormattedDate
import ru.alexbur.koshelok.data.extentions.getTime
import ru.alexbur.koshelok.data.mappers.IntToTypeCategoryMapper
import ru.alexbur.koshelok.data.service.api.TransactionApi
import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity
import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem
import ru.alexbur.koshelok.domain.enums.Currency
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

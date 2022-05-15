package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyDbToEntityMapper
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import javax.inject.Inject

class WalletDbToEntityMapper @Inject constructor(
    private val currencyMapper: CurrencyDbToEntityMapper
) : (WalletDb) -> WalletEntity {

    override fun invoke(walletDb: WalletDb): WalletEntity =
        WalletEntity(
            id = walletDb.id,
            name = walletDb.name,
            amountMoney = walletDb.amountMoney,
            incomeMoney = walletDb.income,
            consumptionMoney = walletDb.consumption,
            currency = currencyMapper(walletDb.currency),
            isHide = walletDb.isHide,
            limit = walletDb.limit,
            partSpending = walletDb.partSpending
        )
}
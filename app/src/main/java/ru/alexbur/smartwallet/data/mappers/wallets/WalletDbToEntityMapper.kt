package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import javax.inject.Inject

class WalletDbToEntityMapper @Inject constructor() : (WalletDb) -> WalletEntity {

    override fun invoke(walletDb: WalletDb): WalletEntity =
        WalletEntity(
            id = walletDb.id,
            name = walletDb.name,
            amountMoney = walletDb.amountMoney,
            incomeMoney = walletDb.income,
            consumptionMoney = walletDb.consumption,
            currency = Currency.valueOf(walletDb.currency),
            isHide = walletDb.isHide,
            limit = walletDb.limit,
            partSpending = walletDb.partSpending
        )
}
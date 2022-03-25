package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.data.service.api.WalletApi
import javax.inject.Inject

class WalletDbToWalletApiMapper @Inject constructor() :
        (WalletDb) -> WalletApi {
    override fun invoke(walletDb: WalletDb): WalletApi {
        return WalletApi(
            id = walletDb.id,
            name = walletDb.name,
            amountMoney = walletDb.amountMoney,
            income = walletDb.income,
            consumption = walletDb.consumption,
            limit = walletDb.limit,
            currency = walletDb.currency,
            isHide = walletDb.isHide
        )
    }
}

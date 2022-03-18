package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.data.service.api.WalletApi
import javax.inject.Inject

class WalletApiToWalletDbMapper @Inject constructor() : (WalletApi) -> WalletDb {

    override fun invoke(wallet: WalletApi): WalletDb {
        return WalletDb(
            id = wallet.id ?: 0,
            name = wallet.name,
            amountMoney = wallet.amountMoney,
            income = wallet.income,
            consumption = wallet.consumption,
            limit = wallet.limit,
            currency = wallet.currency,
            isExceededLimit = wallet.isExceededLimit,
            isHide = wallet.isHide
        )
    }
}

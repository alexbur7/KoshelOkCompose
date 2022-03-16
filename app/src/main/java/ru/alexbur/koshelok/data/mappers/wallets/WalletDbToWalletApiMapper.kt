package ru.alexbur.koshelok.data.mappers.wallets

import ru.alexbur.koshelok.data.db.entity.WalletDb
import ru.alexbur.koshelok.data.service.api.WalletApi
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
            isExceededLimit = walletDb.isExceededLimit,
            isHide = walletDb.isHide
        )
    }
}

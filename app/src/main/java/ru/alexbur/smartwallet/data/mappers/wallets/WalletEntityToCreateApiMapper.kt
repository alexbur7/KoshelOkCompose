package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.service.api.CreateWalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import javax.inject.Inject

class WalletEntityToCreateApiMapper @Inject constructor() : (WalletEntity) -> CreateWalletApi {

    override fun invoke(wallet: WalletEntity): CreateWalletApi {
        return CreateWalletApi(
            name = wallet.name,
            amountMoney = wallet.amountMoney,
            income = wallet.incomeMoney,
            consumption = wallet.consumptionMoney,
            limit = wallet.limit,
            currencyId = wallet.currency.id ?: 0L,
            isHide = wallet.isHide
        )
    }
}
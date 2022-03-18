package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.enums.Currency
import javax.inject.Inject

class WalletApiToHeaderWalletMapper @Inject constructor() :
        (WalletApi) -> DetailWalletItem.HeaderDetailWallet {

    override operator fun invoke(walletApi: WalletApi) =
        DetailWalletItem.HeaderDetailWallet(
            nameWallet = walletApi.name,
            amountMoney = walletApi.amountMoney,
            income = walletApi.income,
            consumption = walletApi.consumption,
            limit = walletApi.limit,
            currency = Currency.valueOf(walletApi.currency),
            isExceededLimit = walletApi.isExceededLimit
        )
}

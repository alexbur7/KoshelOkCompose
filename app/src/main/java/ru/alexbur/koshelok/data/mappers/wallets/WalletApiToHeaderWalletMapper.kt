package ru.alexbur.koshelok.data.mappers.wallets

import ru.alexbur.koshelok.data.service.api.WalletApi
import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem
import ru.alexbur.koshelok.domain.enums.Currency
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

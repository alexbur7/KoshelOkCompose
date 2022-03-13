package ru.alexbur.koshelok.data.mappers.wallets

import ru.alexbur.koshelok.data.service.api.WalletApi
import ru.alexbur.koshelok.domain.entities.wallet.WalletEntity
import ru.alexbur.koshelok.domain.enums.Currency
import javax.inject.Inject

class WalletApiToWalletEntityMapper @Inject constructor() : (WalletApi) -> WalletEntity {

    override operator fun invoke(walletApi: WalletApi) =
        WalletEntity(
            id = walletApi.id ?: 0,
            name = walletApi.name,
            amountMoney = walletApi.amountMoney,
            currency = Currency.valueOf(walletApi.currency),
            isHide = walletApi.isHide
        )
}

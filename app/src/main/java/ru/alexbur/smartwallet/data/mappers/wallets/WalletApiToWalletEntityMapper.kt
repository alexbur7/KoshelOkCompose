package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import javax.inject.Inject

class WalletApiToWalletEntityMapper @Inject constructor() : (WalletApi) -> WalletEntity {

    override operator fun invoke(walletApi: WalletApi) =
        WalletEntity(
            id = walletApi.id ?: 0,
            name = walletApi.name,
            amountMoney = walletApi.amountMoney,
            currency = Currency.valueOf(walletApi.currency),
            isHide = walletApi.isHide,
            limit = walletApi.limit,
            partSpending = calculatePartSpending(
                limit = walletApi.limit,
                consumption = walletApi.consumption
            )
        )

    private fun calculatePartSpending(limit: String?, consumption: String): Float? {
        return if (limit != null && limit.toFloat() > 0) {
            if (consumption.toFloat() > limit.toFloat()) {
                1f
            } else {
                consumption.toFloat() / limit.toFloat()
            }
        } else {
            null
        }
    }
}

package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import javax.inject.Inject

class WalletApiToWalletEntityMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToEntityMapper
) : (WalletApi) -> WalletEntity {

    override operator fun invoke(walletApi: WalletApi) =
        WalletEntity(
            id = walletApi.id,
            name = walletApi.name,
            amountMoney = walletApi.amountMoney.defaultMoney(),
            incomeMoney = walletApi.income.defaultMoney(),
            consumptionMoney = walletApi.consumption.defaultMoney(),
            currency = currencyMapper(walletApi.currency),
            isHide = walletApi.isHide,
            limit = walletApi.limit,
            partSpending = calculatePartSpending(
                limit = walletApi.limit,
                consumption = walletApi.consumption.defaultMoney()
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

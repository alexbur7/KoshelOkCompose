package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.data.service.api.WalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import javax.inject.Inject

class WalletApiToWalletEntityMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToEntityMapper
) : (ResponseApi<WalletApi>) -> WalletEntity {

    override operator fun invoke(responseApi: ResponseApi<WalletApi>) =
        WalletEntity(
            id = responseApi.result.id,
            name = responseApi.result.name,
            amountMoney = responseApi.result.amountMoney.defaultMoney(),
            incomeMoney = responseApi.result.income.defaultMoney(),
            consumptionMoney = responseApi.result.consumption.defaultMoney(),
            currency = currencyMapper(ResponseApi(responseApi.result.currency)),
            isHide = responseApi.result.isHide,
            limit = responseApi.result.limit,
            partSpending = calculatePartSpending(
                limit = responseApi.result.limit,
                consumption = responseApi.result.consumption.defaultMoney()
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

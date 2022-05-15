package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToDbMapper
import ru.alexbur.smartwallet.data.service.api.WalletApi
import javax.inject.Inject

class WalletApiToDbMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToDbMapper
) : (String, WalletApi) -> WalletDb {

    override fun invoke(email: String, wallet: WalletApi): WalletDb {
        return WalletDb(
            id = wallet.id,
            name = wallet.name,
            amountMoney = wallet.amountMoney.defaultMoney(),
            income = wallet.income.defaultMoney(),
            consumption = wallet.consumption.defaultMoney(),
            limit = wallet.limit,
            currency = currencyMapper(wallet.currency),
            isHide = wallet.isHide,
            email = email,
            partSpending = calculatePartSpending(
                limit = wallet.limit,
                consumption = wallet.consumption.defaultMoney()
            )
        )
    }

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

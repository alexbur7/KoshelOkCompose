package ru.alexbur.smartwallet.data.mappers.wallets

import ru.alexbur.smartwallet.data.db.entity.WalletDb
import ru.alexbur.smartwallet.data.service.api.WalletApi
import javax.inject.Inject

class WalletApiToDbMapper @Inject constructor() : (String, WalletApi) -> WalletDb {

    override fun invoke(email: String, wallet: WalletApi): WalletDb {
        return WalletDb(
            id = wallet.id ?: 0,
            name = wallet.name,
            amountMoney = wallet.amountMoney,
            income = wallet.income,
            consumption = wallet.consumption,
            limit = wallet.limit,
            currency = wallet.currency,
            isHide = wallet.isHide,
            email = email,
            partSpending = calculatePartSpending(
                limit = wallet.limit,
                consumption = wallet.consumption
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

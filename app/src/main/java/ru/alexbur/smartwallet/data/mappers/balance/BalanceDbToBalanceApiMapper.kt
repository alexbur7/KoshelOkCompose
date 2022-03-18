package ru.alexbur.smartwallet.data.mappers.balance

import ru.alexbur.smartwallet.data.db.entity.BalanceDb
import ru.alexbur.smartwallet.data.service.api.BalanceApi
import javax.inject.Inject

class BalanceDbToBalanceApiMapper @Inject constructor() : (BalanceDb) -> BalanceApi {
    override fun invoke(balance: BalanceDb): BalanceApi {
        return BalanceApi(
            amountMoney = balance.amountMoney,
            incomeMoney = balance.income,
            consumptionMoney = balance.consumption
        )
    }
}

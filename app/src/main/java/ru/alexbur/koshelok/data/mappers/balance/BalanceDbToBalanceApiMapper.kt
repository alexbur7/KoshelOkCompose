package ru.alexbur.koshelok.data.mappers.balance

import ru.alexbur.koshelok.data.db.entity.BalanceDb
import ru.alexbur.koshelok.data.service.api.BalanceApi
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

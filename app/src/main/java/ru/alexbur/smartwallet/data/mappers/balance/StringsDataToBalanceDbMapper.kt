package ru.alexbur.smartwallet.data.mappers.balance

import ru.alexbur.smartwallet.data.db.entity.BalanceDb
import ru.alexbur.smartwallet.data.service.api.BalanceApi
import javax.inject.Inject

class StringsDataToBalanceDbMapper @Inject constructor() :
        (String, BalanceApi) -> BalanceDb {
    override fun invoke(
        email: String,
        balanceApi: BalanceApi
    ): BalanceDb {
        return BalanceDb(
            email = email,
            amountMoney = balanceApi.amountMoney,
            income = balanceApi.incomeMoney,
            consumption = balanceApi.consumptionMoney
        )
    }
}

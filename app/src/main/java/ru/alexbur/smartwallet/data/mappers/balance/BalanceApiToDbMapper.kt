package ru.alexbur.smartwallet.data.mappers.balance

import ru.alexbur.smartwallet.data.db.entity.BalanceDb
import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.service.api.BalanceApi
import javax.inject.Inject

class BalanceApiToDbMapper @Inject constructor() :
        (String, BalanceApi) -> BalanceDb {
    override fun invoke(
        email: String,
        balanceApi: BalanceApi
    ): BalanceDb {
        return BalanceDb(
            email = email,
            amountMoney = balanceApi.amountMoney.defaultMoney(),
            income = balanceApi.incomeMoney.defaultMoney(),
            consumption = balanceApi.consumptionMoney.defaultMoney()
        )
    }
}

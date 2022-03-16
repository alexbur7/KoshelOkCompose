package ru.alexbur.koshelok.data.mappers.balance

import ru.alexbur.koshelok.data.db.entity.BalanceDb
import javax.inject.Inject

class StringsDataToBalanceDbMapper @Inject constructor() :
        (String, String, String, String) -> BalanceDb {
    override fun invoke(
        email: String,
        amountMoney: String,
        incomeMoney: String,
        consumptionMoney: String
    ): BalanceDb {
        return BalanceDb(
            email = email,
            amountMoney = amountMoney,
            income = incomeMoney,
            consumption = consumptionMoney
        )
    }
}

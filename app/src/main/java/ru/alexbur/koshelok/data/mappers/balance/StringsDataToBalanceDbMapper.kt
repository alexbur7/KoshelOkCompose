package ru.alexbur.koshelok.data.mappers.balance

import ru.alexbur.koshelok.data.db.entity.BalanceDb
import javax.inject.Inject

class StringsDataToBalanceDbMapper @Inject constructor() :
        (Long, String, String, String) -> BalanceDb {
    override fun invoke(
        personId: Long,
        amountMoney: String,
        incomeMoney: String,
        consumptionMoney: String
    ): BalanceDb {
        return BalanceDb(
            personId = personId,
            amountMoney = amountMoney,
            income = incomeMoney,
            consumption = consumptionMoney
        )
    }
}

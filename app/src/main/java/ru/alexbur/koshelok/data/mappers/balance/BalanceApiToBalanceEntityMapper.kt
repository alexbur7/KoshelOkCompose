package ru.alexbur.koshelok.data.mappers.balance

import ru.alexbur.koshelok.domain.entities.listwallet.BalanceEntity
import javax.inject.Inject

class BalanceApiToBalanceEntityMapper @Inject constructor() :
        (String, String, String) -> BalanceEntity {

    override operator fun invoke(balance: String, income: String, consumption: String) =
        BalanceEntity(
            amountMoney = balance,
            incomeMoney = income,
            consumptionMoney = consumption
        )
}

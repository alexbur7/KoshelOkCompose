package ru.alexbur.smartwallet.data.mappers.balance

import ru.alexbur.smartwallet.data.service.api.BalanceApi
import ru.alexbur.smartwallet.domain.entities.listwallet.BalanceEntity
import javax.inject.Inject

class BalanceApiToBalanceEntityMapper @Inject constructor() :
        (BalanceApi) -> BalanceEntity {

    override fun invoke(balance: BalanceApi): BalanceEntity =
        BalanceEntity(
            amountMoney = balance.amountMoney,
            incomeMoney = balance.incomeMoney,
            consumptionMoney = balance.consumptionMoney
        )
}

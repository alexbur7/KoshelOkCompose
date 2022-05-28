package ru.alexbur.smartwallet.data.mappers.balance

import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.service.api.BalanceApi
import ru.alexbur.smartwallet.domain.entities.listwallet.BalanceEntity
import javax.inject.Inject

class BalanceApiToBalanceEntityMapper @Inject constructor() :
        (BalanceApi) -> BalanceEntity {

    override fun invoke(balance: BalanceApi): BalanceEntity =
        BalanceEntity(
            amountMoney = balance.amountMoney.defaultMoney(),
            incomeMoney = balance.incomeMoney.defaultMoney(),
            consumptionMoney = balance.consumptionMoney.defaultMoney()
        )
}

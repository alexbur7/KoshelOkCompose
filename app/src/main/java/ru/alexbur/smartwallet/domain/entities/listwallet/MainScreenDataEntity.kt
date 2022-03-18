package ru.alexbur.smartwallet.domain.entities.listwallet

import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency

data class MainScreenDataEntity(
    val balanceEntity: BalanceEntity,
    val exchangeRatesEntity: ExchangeRatesEntity,
    val wallets: List<WalletEntity>
) {
    companion object {
        val shimmerData = MainScreenDataEntity(
            balanceEntity = BalanceEntity(
                amountMoney = "",
                incomeMoney = "",
                consumptionMoney = ""
            ),
            exchangeRatesEntity = ExchangeRatesEntity(
                firstCourse = "",
                firstCurrency = Currency.EUR,
                firstIsUp = true,
                secondCourse = "",
                secondCurrency = Currency.EUR,
                secondIsUp = true,
                thirdCourse = "",
                thirdCurrency = Currency.EUR,
                thirdIsUp = true,
            ),
            wallets = mutableListOf<WalletEntity>().apply {
                repeat(10) {
                    add(
                        WalletEntity(
                            id = it.toLong(),
                            name = "",
                            amountMoney = "",
                            currency = Currency.RUB,
                            isHide = false
                        )
                    )
                }
            }.toList()
        )
    }
}
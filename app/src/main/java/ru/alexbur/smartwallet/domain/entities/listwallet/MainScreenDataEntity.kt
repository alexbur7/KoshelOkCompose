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
                firstCurrency = CurrencyEntity(
                    Currency.RUB,
                    "3",
                    true
                ),
                secondCurrency = CurrencyEntity(
                    Currency.EUR,
                    "86",
                    true
                ),
                thirdCurrency = CurrencyEntity(
                    Currency.USD,
                    "78",
                    true
                )
            ),
            wallets = mutableListOf<WalletEntity>().apply {
                repeat(10) {
                    add(
                        WalletEntity(
                            id = it.toLong(),
                            name = "",
                            amountMoney = "",
                            currency = Currency.RUB,
                            isHide = false,
                            limit = null,
                            partSpending = null
                        )
                    )
                }
            }.toList()
        )

        val emptyData = MainScreenDataEntity(
            balanceEntity = BalanceEntity(
                amountMoney = "0",
                incomeMoney = "0",
                consumptionMoney = "0"
            ),
            exchangeRatesEntity = ExchangeRatesEntity(
                firstCurrency = CurrencyEntity(
                    Currency.CHF,
                    "",
                    true
                ),
                secondCurrency = CurrencyEntity(
                    Currency.EUR,
                    "",
                    true
                ),
                thirdCurrency = CurrencyEntity(
                    Currency.USD,
                    "",
                    true
                )
            ),
            wallets = emptyList()
        )
    }
}
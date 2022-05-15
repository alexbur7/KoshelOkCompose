package ru.alexbur.smartwallet.domain.entities.listwallet

import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

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
                firstCurrency = CurrencyEntity.default,
                secondCurrency = CurrencyEntity.default,
                thirdCurrency = CurrencyEntity.default
            ),
            wallets = mutableListOf<WalletEntity>().apply {
                repeat(3) {
                    add(
                        WalletEntity(
                            id = it.toLong(),
                            name = "",
                            amountMoney = "",
                            incomeMoney = "",
                            consumptionMoney = "",
                            currency = CurrencyEntity.default,
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
                firstCurrency = CurrencyEntity.default,
                secondCurrency = CurrencyEntity.default,
                thirdCurrency = CurrencyEntity.default
            ),
            wallets = emptyList()
        )
    }
}
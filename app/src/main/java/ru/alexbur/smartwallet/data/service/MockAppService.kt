package ru.alexbur.smartwallet.data.service

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import ru.alexbur.smartwallet.data.service.api.*

class MockAppService : AppService {
    
    override suspend fun getWallets(): List<WalletApi> {
        return getDataForMainScreen().wallets
    }

    override suspend fun getTransaction(walletId: Long): List<TransactionApi> {
        return listOf(
            TransactionApi(
                id = 1,
                money = "2512",
                idCategory = 12,
                type = 1,
                operation = "35315",
                idIcon = 24,
                color = 21,
                currency = "USD",
                time = 2125156662
            ),
            TransactionApi(
                id = 2,
                money = "251215",
                idCategory = 12,
                type = 1,
                operation = "35315",
                idIcon = 24,
                color = 21,
                currency = "USD",
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                idCategory = 12,
                type = 0,
                operation = "35315",
                idIcon = 24,
                color = 21,
                currency = "RUB",
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                idCategory = 12,
                type = 1,
                operation = "35315",
                idIcon = 24,
                color = 21,
                currency = "RUB",
                time = 2125156662
            )
        )
    }

    override suspend fun createWallet(walletApi: WalletApi): WalletApi {
        delay(3000L)
        return WalletApi(
            id = 10,
            name = "Кошелек 124",
            amountMoney = "14000",
            income = "14000",
            consumption = "18000",
            limit = "15000",
            currency = "RUB",
            isHide = false
        )
    }

    override suspend fun deleteWallet(walletId: Long): Boolean {
        return true
    }

    override suspend fun getDataForMainScreen(): MainScreenDataApi {
        delay(3000)
        return MainScreenDataApi(
            balance = BalanceApi(
                amountMoney = "1000245",
                incomeMoney = "1251959",
                consumptionMoney = "21451"
            ),
            exchangeRatesApi = ExchangeRatesApi(
                firstCurrency = CurrencyApi(
                    name = "RUB",
                    course = "69.1",
                    isUp = false
                ),
                secondCurrency = CurrencyApi(
                    name = "EUR",
                    course = "39.1",
                    isUp = true
                ),
                thirdCurrency = CurrencyApi(
                    name = "USD",
                    course = "81.1",
                    isUp = false
                )
            ),
            wallets = listOf(
                WalletApi(
                    id = 15,
                    name = "Кошелек 12524",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = "RUB",
                    isHide = false
                ), WalletApi(
                    id = 10,
                    name = "Кошелек 1124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = "RUB",
                    isHide = false
                ), WalletApi(
                    id = 11,
                    name = "Кошелек15 124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = "USD",
                    isHide = false
                ), WalletApi(
                    id = 14,
                    name = "Кошелек 124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = null,
                    currency = "RUB",
                    isHide = false
                ), WalletApi(
                    id = 20,
                    name = "Кошелек 12154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = "EUR",
                    isHide = false
                )
            )
        )
    }

    override suspend fun createTransaction(transactionApi: CreateTransactionApi): TransactionApi {
        delay(3000L)
        return TransactionApi(
            id = 65,
            money = "19512",
            idCategory = 124,
            type = 0,
            operation = "5831758",
            idIcon = 24,
            color = Color.Black.value.toInt(),
            currency = "RUB",
            time = 153531599153
        )
    }

    override suspend fun editTransaction(
        id: Long,
        transactionApi: CreateTransactionApi
    ): TransactionApi {
        delay(3000L)
        return TransactionApi(
            id = 72,
            money = "1952112",
            idCategory = 124,
            type = 0,
            operation = "51831758",
            idIcon = 24,
            color = Color.Black.value.toInt(),
            currency = "RUB",
            time = 153531599246
        )
    }

    override suspend fun deleteTransaction(id: Long): Boolean {
        delay(3000L)
        return true
    }

    override suspend fun getCategories(type: Int): List<CategoryApi> {
        delay(3000L)
        return listOf()
    }

    override suspend fun createCategory(categoryApi: CategoryApi): CategoryApi {
        delay(3000L)
        return CategoryApi(12, 0, "GJbajng", 2, 3)
    }

    override suspend fun updateToken(userApi: UserApi): String {
        delay(3000L)
        return "New_token"
    }
}
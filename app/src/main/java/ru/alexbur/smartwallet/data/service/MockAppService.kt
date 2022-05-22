package ru.alexbur.smartwallet.data.service

import kotlinx.coroutines.delay
import ru.alexbur.smartwallet.data.service.api.*
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

class MockAppService : AppService, AuthService {

    override suspend fun getWallets(): List<WalletApi> {
        delay(3000L)
        return getDataForMainScreen().wallets
    }

    override suspend fun getTransaction(walletId: Long): List<TransactionApi> {
        delay(3000L)
        return listOf(
            TransactionApi(
                id = 1,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 2,
                money = "251215",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
            TransactionApi(
                id = 3,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ), TransactionApi(
                id = 4,
                money = "2512",
                category = CategoryApi(
                    0, 0, "gwegew", 1
                ),
                currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                time = 2125156662
            ),
        )
    }

    override suspend fun createWallet(walletApi: CreateWalletApi): WalletApi {
        delay(3000L)
        return WalletApi(
            id = 10,
            name = "Кошелек 124",
            amountMoney = "14000",
            income = "14000",
            consumption = "18000",
            limit = "15000",
            currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
            isHide = false
        )
    }

    override suspend fun deleteWallet(walletId: Long): Boolean {
        delay(3000L)
        return true
    }

    override suspend fun getDataForMainScreen(): MainScreenDataApi {
        delay(2000)
        return MainScreenDataApi(
            balance = BalanceApi(
                amountMoney = "1000245",
                incomeMoney = "1251959",
                consumptionMoney = "21451"
            ),
            exchangeRatesApi = ExchangeRatesApi(
                firstCurrency  = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                secondCurrency = CurrencyApi(1,"EUR","65.4","Евро","Евро EUR","!", false),
                thirdCurrency = CurrencyApi(2,"FRA","22.4","Евро","Евро EUR","!", false),
            ),
            wallets = listOf(
                WalletApi(
                    id = 15,
                    name = "Кошелек 12524",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ), WalletApi(
                    id = 10,
                    name = "Кошелек 1124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ), WalletApi(
                    id = 11,
                    name = "Кошелек15 124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = "15000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ), WalletApi(
                    id = 14,
                    name = "Кошелек 124",
                    amountMoney = "14000",
                    income = "14000",
                    consumption = "18000",
                    limit = null,
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ), WalletApi(
                    id = 40,
                    name = "Wallt 12154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 120,
                    name = "Wallets 12154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 21,
                    name = "Wal 154",
                    amountMoney = "150",
                    income = "1000",
                    consumption = "8000",
                    limit = "1100",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
                WalletApi(
                    id = 30,
                    name = "Кошелек154",
                    amountMoney = "15000",
                    income = "114000",
                    consumption = "158000",
                    limit = "125000",
                    currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
                    isHide = false
                ),
            )
        )
    }

    override suspend fun createTransaction(transactionApi: CreateTransactionApi): TransactionApi {
        delay(3000L)
        return TransactionApi(
            id = 65,
            money = "19512",
            category = CategoryApi(0,0,"fqwgeg",5),
            currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
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
            category = CategoryApi(0,0,"fqwgeg",5),
            currency = CurrencyApi(0,"USD","62.4","Евро","Евро EUR","!", false),
            time = 153531599246
        )
    }

    override suspend fun deleteTransaction(id: Long): Boolean {
        delay(3000L)
        return true
    }

    override suspend fun getCategories(): List<CategoryApi> {
        delay(3000L)
        return listOf(
            CategoryApi(
                0,
                type = 0,
                operation = "Супермаркет",
                idIcon = 1
            ),
            CategoryApi(
                1,
                type = 1,
                operation = "Супермаркет2",
                idIcon = 3
            ),
            CategoryApi(
                2,
                type = 1,
                operation = "Супермаркет3",
                idIcon = 2
            ),
            CategoryApi(
                3,
                type = 0,
                operation = "Супермаркет4",
                idIcon = 4
            ),
            CategoryApi(
                4,
                type = 1,
                operation = "Супермаркет4",
                idIcon = 4
            ),
            CategoryApi(
                5,
                type = 1,
                operation = "Супермаркет5",
                idIcon = 19
            ),
            CategoryApi(
                6,
                type = 0,
                operation = "Супермаркет6",
                idIcon = 4
            ),
            CategoryApi(
                7,
                type = 0,
                operation = "Супермаркет7",
                idIcon = 12
            ),
            CategoryApi(
                8,
                type = 0,
                operation = "Супермаркет 8",
                idIcon = 6
            ),
            CategoryApi(
                9,
                type = 1,
                operation = "Супермаркет10",
                idIcon = 8
            ),
        )
    }

    override suspend fun createCategory(categoryApi: CategoryApi): CategoryApi {
        delay(1000L)
        return CategoryApi(19, 0, "GJbafkwjng", 2)
    }

    override suspend fun updateToken(userApi: UserApi): String {
        delay(3000L)
        return "New_token"
    }

    override suspend fun getCurrencies(): List<CurrencyApi> {
        delay(1000L)
        return listOf(
            CurrencyApi(0,"UER", "521","qrqtg","gwehh","e",false),
            CurrencyApi(1,"USD", "50","rhkn","gwqjowehh","e",false),
            CurrencyApi(2,"GJN", "56","qagn","gweqjfnwhh","e",false),
            CurrencyApi(3,"GQg", "19","qfqgkm","gwqjfnehh","e",false),
            CurrencyApi(4,"Geg", "51","anwg","qjngwehh","e",false),
            CurrencyApi(5,"GEQ", "19","qwqtg","gqnt3jwehh","e",false),
            CurrencyApi(6,"OJJ", "521","htg","gweqtjhh","e",false),
        )
    }
}
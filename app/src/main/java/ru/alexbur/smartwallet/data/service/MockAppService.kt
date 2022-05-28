package ru.alexbur.smartwallet.data.service

import kotlinx.coroutines.delay
import ru.alexbur.smartwallet.data.service.api.*

class MockAppService : AppService, AuthService {

    override suspend fun getWallets(): ResponseApi<List<WalletApi>> {
        delay(3000L)
        return ResponseApi(getDataForMainScreen().result.wallets)
    }

    override suspend fun getTransaction(walletId: Long): ResponseApi<List<TransactionApi>> {
        delay(3000L)
        return ResponseApi(
            listOf(
                TransactionApi(
                    id = 1,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 2,
                    money = "251215",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 3,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
                TransactionApi(
                    id = 4,
                    money = "2512",
                    category = CategoryApi(
                        0, true, "gwegew", 1
                    ),
                    currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                    time = 2125156662
                ),
            )
        )
    }

    override suspend fun createWallet(walletApi: CreateWalletApi): ResponseApi<WalletApi> {
        delay(3000L)
        return ResponseApi(
            WalletApi(
                id = 10,
                name = "Кошелек 124",
                amountMoney = "14000",
                income = "14000",
                consumption = "18000",
                limit = "15000",
                currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                isHide = false
            )
        )
    }

    override suspend fun editWallet(id: Long, walletApi: CreateWalletApi): ResponseApi<WalletApi> {
        return ResponseApi(
            WalletApi(
                id = 10,
                name = "Кошелек 124",
                amountMoney = "14000",
                income = "14000",
                consumption = "18000",
                limit = "15000",
                currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                isHide = false
            )
        )
    }

    override suspend fun deleteWallet(walletId: Long): ResponseApi<Boolean> {
        delay(3000L)
        return ResponseApi(true)
    }

    override suspend fun getDataForMainScreen(): ResponseApi<MainScreenDataApi> {
        delay(2000)
        return ResponseApi(
            MainScreenDataApi(
                balance = BalanceApi(
                    amountMoney = "1000245",
                    incomeMoney = "1251959",
                    consumptionMoney = "21451"
                ),
                exchangeRatesApi = listOf(),
                wallets = listOf(
                    WalletApi(
                        id = 15,
                        name = "Кошелек 12524",
                        amountMoney = "14000",
                        income = "14000",
                        consumption = "18000",
                        limit = "15000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 10,
                        name = "Кошелек 1124",
                        amountMoney = "14000",
                        income = "14000",
                        consumption = "18000",
                        limit = "15000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 11,
                        name = "Кошелек15 124",
                        amountMoney = "14000",
                        income = "14000",
                        consumption = "18000",
                        limit = "15000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 14,
                        name = "Кошелек 124",
                        amountMoney = "14000",
                        income = "14000",
                        consumption = "18000",
                        limit = null,
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 40,
                        name = "Wallt 12154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 120,
                        name = "Wallets 12154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 21,
                        name = "Wal 154",
                        amountMoney = "150",
                        income = "1000",
                        consumption = "8000",
                        limit = "1100",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                    WalletApi(
                        id = 30,
                        name = "Кошелек154",
                        amountMoney = "15000",
                        income = "114000",
                        consumption = "158000",
                        limit = "125000",
                        currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                        isHide = false
                    ),
                )
            )
        )
    }

    override suspend fun createTransaction(transactionApi: CreateTransactionApi): ResponseApi<TransactionApi> {
        delay(3000L)
        return ResponseApi(
            TransactionApi(
                id = 65,
                money = "19512",
                category = CategoryApi(0, true, "fqwgeg", 5),
                currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                time = 153531599153
            )
        )
    }

    override suspend fun editTransaction(
        id: Long,
        transactionApi: CreateTransactionApi
    ): ResponseApi<TransactionApi> {
        delay(3000L)
        return ResponseApi(
            TransactionApi(
                id = 72,
                money = "1952112",
                category = CategoryApi(0, true, "fqwgeg", 5),
                currency = CurrencyApi(0, "USD", "62.4", "Евро", "Евро EUR", "!", false),
                time = 153531599246
            )
        )
    }

    override suspend fun deleteTransaction(id: Long): ResponseApi<Boolean> {
        delay(3000L)
        return ResponseApi(true)
    }

    override suspend fun getCategories(): ResponseApi<List<CategoryApi>> {
        delay(3000L)
        return ResponseApi(
            listOf(
                CategoryApi(
                    0,
                    type = false,
                    operation = "Супермаркет",
                    idIcon = 1
                ),
                CategoryApi(
                    1,
                    type = true,
                    operation = "Супермаркет2",
                    idIcon = 3
                ),
                CategoryApi(
                    2,
                    type = true,
                    operation = "Супермаркет3",
                    idIcon = 2
                ),
                CategoryApi(
                    3,
                    type = true,
                    operation = "Супермаркет4",
                    idIcon = 4
                ),
                CategoryApi(
                    4,
                    type = true,
                    operation = "Супермаркет4",
                    idIcon = 4
                ),
                CategoryApi(
                    5,
                    type = false,
                    operation = "Супермаркет5",
                    idIcon = 19
                ),
                CategoryApi(
                    6,
                    type = false,
                    operation = "Супермаркет6",
                    idIcon = 4
                ),
                CategoryApi(
                    7,
                    type = true,
                    operation = "Супермаркет7",
                    idIcon = 12
                ),
                CategoryApi(
                    8,
                    type = false,
                    operation = "Супермаркет 8",
                    idIcon = 6
                ),
                CategoryApi(
                    9,
                    type = true,
                    operation = "Супермаркет10",
                    idIcon = 8
                ),
            )
        )
    }

    override suspend fun createCategory(categoryApi: CategoryApi): ResponseApi<CategoryApi> {
        delay(1000L)
        return ResponseApi(CategoryApi(19, true, "GJbafkwjng", 2))
    }

    override suspend fun updateToken(userApi: UserApi): ResponseApi<String> {
        delay(3000L)
        return ResponseApi("New_token")
    }

    override suspend fun getCurrencies(): ResponseApi<List<CurrencyApi>> {
        delay(1000L)
        return ResponseApi(
            listOf(
                CurrencyApi(0, "UER", "521", "qrqtg", "gwehh", "e", false),
                CurrencyApi(1, "USD", "50", "rhkn", "gwqjowehh", "e", false),
                CurrencyApi(2, "GJN", "56", "qagn", "gweqjfnwhh", "e", false),
                CurrencyApi(3, "GQg", "19", "qfqgkm", "gwqjfnehh", "e", false),
                CurrencyApi(4, "Geg", "51", "anwg", "qjngwehh", "e", false),
                CurrencyApi(5, "GEQ", "19", "qwqtg", "gqnt3jwehh", "e", false),
                CurrencyApi(6, "OJJ", "521", "htg", "gweqtjhh", "e", false),
            )
        )
    }
}
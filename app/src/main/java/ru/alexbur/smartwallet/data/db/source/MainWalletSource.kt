package ru.alexbur.smartwallet.data.db.source

import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import ru.alexbur.smartwallet.data.mappers.MainScreenDataDbToApiMapper
import ru.alexbur.smartwallet.data.mappers.balance.BalanceApiToDbMapper
import ru.alexbur.smartwallet.data.mappers.exchangerates.ExchangeRatesApiToDbMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToDbMapper
import ru.alexbur.smartwallet.data.service.api.MainScreenDataApi
import javax.inject.Inject

interface MainWalletSource {

    suspend fun getMainScreenData(email: String): Result<MainScreenDataApi>

    suspend fun insertMainScreenData(
        email: String,
        mainScreenDataApi: MainScreenDataApi
    )
}

class MainWalletSourceImpl @Inject constructor(
    private val database: SmartWalletDatabase,
    private val balanceMapper: BalanceApiToDbMapper,
    private val exchangeRatesMapper: ExchangeRatesApiToDbMapper,
    private val walletDbMapper: WalletApiToDbMapper,
    private val mainScreenMapper: MainScreenDataDbToApiMapper
) : MainWalletSource {

    override suspend fun getMainScreenData(email: String): Result<MainScreenDataApi> {
        val mainScreen = database.getMainScreenDao()
            .getMainScreenData(email)
        val resultDb = if (mainScreen != null) {
            Result.success(mainScreen)
        } else {
            Result.failure(exception = Throwable("MainScreenData is null"))
        }
        return resultDb
            .map(mainScreenMapper)
    }

    override suspend fun insertMainScreenData(
        email: String,
        mainScreenDataApi: MainScreenDataApi
    ) {
        database.getMainScreenDao().insertMainScreenData(
            balanceMapper(
                email,
                mainScreenDataApi.balance
            ),
            exchangeRatesMapper(email = email,mainScreenDataApi.exchangeRatesApi),
            mainScreenDataApi.wallets.map { walletDbMapper(email,it) }
        )
    }
}

package ru.alexbur.koshelok.data.db.source

import ru.alexbur.koshelok.data.db.KoshelokDatabase
import ru.alexbur.koshelok.data.mappers.MainScreenDataDbToApiMapper
import ru.alexbur.koshelok.data.mappers.balance.StringsDataToBalanceDbMapper
import ru.alexbur.koshelok.data.mappers.exchangerates.ExchangeRatesApiToDbMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToWalletDbMapper
import ru.alexbur.koshelok.data.service.api.MainScreenDataApi
import javax.inject.Inject

interface MainWalletSource {

    suspend fun getMainScreenData(email: String): Result<MainScreenDataApi>

    suspend fun insertMainScreenData(
        email: String,
        mainScreenDataApi: MainScreenDataApi
    )
}

class MainWalletSourceImpl @Inject constructor(
    private val database: KoshelokDatabase,
    private val balanceMapper: StringsDataToBalanceDbMapper,
    private val exchangeRatesMapper: ExchangeRatesApiToDbMapper,
    private val walletDbMapper: WalletApiToWalletDbMapper,
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
                mainScreenDataApi.balance,
                mainScreenDataApi.income,
                mainScreenDataApi.consumption
            ),
            exchangeRatesMapper(mainScreenDataApi.exchangeRatesApi),
            mainScreenDataApi.wallets.map(walletDbMapper)
        )
    }
}

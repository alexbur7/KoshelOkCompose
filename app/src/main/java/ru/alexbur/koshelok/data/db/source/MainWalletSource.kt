package ru.alexbur.koshelok.data.db.source

import ru.alexbur.koshelok.data.db.KoshelokDatabase
import ru.alexbur.koshelok.data.mappers.MainScreenDataDbToApiMapper
import ru.alexbur.koshelok.data.mappers.balance.StringsDataToBalanceDbMapper
import ru.alexbur.koshelok.data.mappers.exchangerates.ExchangeRatesApiToDbMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToWalletDbMapper
import ru.alexbur.koshelok.data.service.api.MainScreenDataApi
import javax.inject.Inject

interface MainWalletSource {

    suspend fun getMainScreenData(personId: Long): Result<MainScreenDataApi>

    suspend fun insertMainScreenData(
        personId: Long,
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

    override suspend fun getMainScreenData(personId: Long): Result<MainScreenDataApi> {
        val mainScreen = database.getMainScreenDao()
            .getMainScreenData(personId)
        val resultDb = if (mainScreen != null) {
            Result.success(mainScreen)
        } else {
            Result.failure(exception = Throwable("MainScreenData is null"))
        }
        return resultDb
            .map(mainScreenMapper)
    }

    override suspend fun insertMainScreenData(
        personId: Long,
        mainScreenDataApi: MainScreenDataApi
    ) {
        database.getMainScreenDao().insertMainScreenData(
            balanceMapper(
                personId,
                mainScreenDataApi.balance,
                mainScreenDataApi.income,
                mainScreenDataApi.consumption
            ),
            exchangeRatesMapper(personId, mainScreenDataApi.exchangeRatesApi),
            mainScreenDataApi.wallets.map(walletDbMapper)
        )
    }
}

package ru.alexbur.koshelok.data.mappers


import ru.alexbur.koshelok.data.db.entity.MainScreenDataDb
import ru.alexbur.koshelok.data.mappers.exchangerates.ExchangeRatesDbToApiMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletDbToWalletApiMapper
import ru.alexbur.koshelok.data.service.api.MainScreenDataApi
import javax.inject.Inject

class MainScreenDataDbToApiMapper @Inject constructor(
    private val exchangeRatesMapper: ExchangeRatesDbToApiMapper,
    private val walletMapper: WalletDbToWalletApiMapper
) : (MainScreenDataDb) -> MainScreenDataApi {
    override fun invoke(mainScreenData: MainScreenDataDb): MainScreenDataApi {
        return MainScreenDataApi(
            balance = mainScreenData.balanceDb.amountMoney,
            income = mainScreenData.balanceDb.income,
            consumption = mainScreenData.balanceDb.consumption,
            exchangeRatesApi = exchangeRatesMapper(mainScreenData.exchangeRatesDb),
            wallets = mainScreenData.wallets.map(walletMapper)
        )
    }
}

package ru.alexbur.smartwallet.data.mappers


import ru.alexbur.smartwallet.data.db.entity.MainScreenDataDb
import ru.alexbur.smartwallet.data.mappers.balance.BalanceDbToBalanceApiMapper
import ru.alexbur.smartwallet.data.mappers.exchangerates.ExchangeRatesDbToApiMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletDbToWalletApiMapper
import ru.alexbur.smartwallet.data.service.api.MainScreenDataApi
import javax.inject.Inject

class MainScreenDataDbToApiMapper @Inject constructor(
    private val exchangeRatesMapper: ExchangeRatesDbToApiMapper,
    private val balanceMapper: BalanceDbToBalanceApiMapper,
    private val walletMapper: WalletDbToWalletApiMapper
) : (MainScreenDataDb) -> MainScreenDataApi {
    override fun invoke(mainScreenData: MainScreenDataDb): MainScreenDataApi {
        return MainScreenDataApi(
            balance = balanceMapper(mainScreenData.balanceDb),
            exchangeRatesApi = exchangeRatesMapper(mainScreenData.exchangeRatesDb),
            wallets = mainScreenData.wallets.map(walletMapper)
        )
    }
}

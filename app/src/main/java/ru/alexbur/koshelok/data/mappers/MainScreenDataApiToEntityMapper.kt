package ru.alexbur.koshelok.data.mappers

import ru.alexbur.koshelok.data.mappers.balance.BalanceApiToBalanceEntityMapper
import ru.alexbur.koshelok.data.mappers.exchangerates.ExchangeRatesApiToExchangeRatesEntityMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToWalletEntityMapper
import ru.alexbur.koshelok.data.service.api.MainScreenDataApi
import ru.alexbur.koshelok.domain.entities.listwallet.MainScreenDataEntity
import javax.inject.Inject

class MainScreenDataApiToEntityMapper @Inject constructor(
    private val balanceMapper: BalanceApiToBalanceEntityMapper,
    private val exchangeRatesMapper: ExchangeRatesApiToExchangeRatesEntityMapper,
    private val walletMapper: WalletApiToWalletEntityMapper
) : (MainScreenDataApi) -> MainScreenDataEntity {

    override operator fun invoke(mainScreenDataApi: MainScreenDataApi) =
        MainScreenDataEntity(
            balanceMapper(
                mainScreenDataApi.balance,
                mainScreenDataApi.income,
                mainScreenDataApi.consumption
            ),
            exchangeRatesMapper(mainScreenDataApi.exchangeRatesApi),
            mainScreenDataApi.wallets.map {
                walletMapper(it)
            }
        )
}

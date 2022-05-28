package ru.alexbur.smartwallet.data.mappers

import ru.alexbur.smartwallet.data.mappers.balance.BalanceApiToBalanceEntityMapper
import ru.alexbur.smartwallet.data.mappers.exchangerates.ExchangeRatesApiToEntityMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToWalletEntityMapper
import ru.alexbur.smartwallet.data.service.api.MainScreenDataApi
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import javax.inject.Inject

class MainScreenDataApiToEntityMapper @Inject constructor(
    private val balanceMapper: BalanceApiToBalanceEntityMapper,
    private val exchangeRatesMapper: ExchangeRatesApiToEntityMapper,
    private val walletMapper: WalletApiToWalletEntityMapper
) : (ResponseApi<MainScreenDataApi>) -> MainScreenDataEntity {

    override operator fun invoke(mainScreenDataApi: ResponseApi<MainScreenDataApi>) =
        MainScreenDataEntity(
            balanceMapper(mainScreenDataApi.result.balance),
            exchangeRatesMapper(mainScreenDataApi.result.exchangeRatesApi),
            mainScreenDataApi.result.wallets.map {
                walletMapper(ResponseApi(it))
            }
        )
}

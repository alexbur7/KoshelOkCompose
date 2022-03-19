package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyDbToApiMapper
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesDbToApiMapper @Inject constructor(
    private val currencyMapper: CurrencyDbToApiMapper
) : (ExchangeRatesDb) -> ExchangeRatesApi {

    override fun invoke(exchangeRates: ExchangeRatesDb): ExchangeRatesApi {
        return ExchangeRatesApi(
            firstCurrency = currencyMapper(exchangeRates.firstCurrency),
            secondCurrency = currencyMapper(exchangeRates.secondCurrency),
            thirdCurrency = currencyMapper(exchangeRates.thirdCurrency),
        )
    }
}

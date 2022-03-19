package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToDbMapper
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesApiToDbMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToDbMapper
) : (ExchangeRatesApi) -> ExchangeRatesDb {
    override fun invoke(exchangeRates: ExchangeRatesApi): ExchangeRatesDb {
        return ExchangeRatesDb(
            firstCurrency = currencyMapper(exchangeRates.firstCurrency),
            secondCurrency = currencyMapper(exchangeRates.secondCurrency),
            thirdCurrency = currencyMapper(exchangeRates.thirdCurrency),
        )
    }
}

package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesApiToDbMapper @Inject constructor() :
        (String, List<CurrencyApi>) -> ExchangeRatesDb {
    override fun invoke(email: String, exchangeRates: List<CurrencyApi>): ExchangeRatesDb {
        return ExchangeRatesDb(
            email = email,
            firstCurrency = exchangeRates[0].name,
            firstCourse = exchangeRates[0].course,
            firstIsUp = exchangeRates[0].isUp,
            secondCurrency = exchangeRates[1].name,
            secondCourse = exchangeRates[1].course,
            secondIsUp = exchangeRates[1].isUp,
            thirdCurrency = exchangeRates[2].name,
            thirdCourse = exchangeRates[2].course,
            thirdIsUp = exchangeRates[2].isUp,
        )
    }
}

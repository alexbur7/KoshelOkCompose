package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesApiToDbMapper @Inject constructor() :
        (String, ExchangeRatesApi) -> ExchangeRatesDb {
    override fun invoke(email: String, exchangeRates: ExchangeRatesApi): ExchangeRatesDb {
        return ExchangeRatesDb(
            email = email,
            firstCurrency = exchangeRates.firstCurrency.name,
            firstCourse = exchangeRates.firstCurrency.course,
            firstIsUp = exchangeRates.firstCurrency.isUp,
            secondCurrency = exchangeRates.secondCurrency.name,
            secondCourse = exchangeRates.secondCurrency.course,
            secondIsUp = exchangeRates.secondCurrency.isUp,
            thirdCurrency = exchangeRates.thirdCurrency.name,
            thirdCourse = exchangeRates.thirdCurrency.course,
            thirdIsUp = exchangeRates.thirdCurrency.isUp,
        )
    }
}

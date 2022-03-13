package ru.alexbur.koshelok.data.mappers.exchangerates

import ru.alexbur.koshelok.data.db.entity.ExchangeRatesDb
import ru.alexbur.koshelok.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesDbToApiMapper @Inject constructor() : (ExchangeRatesDb) -> ExchangeRatesApi {
    override fun invoke(exchangeRates: ExchangeRatesDb): ExchangeRatesApi {
        return ExchangeRatesApi(
            firstCurrency = exchangeRates.firstCurrency,
            firstCourse = exchangeRates.firstCourse,
            firstIsUp = exchangeRates.firstIsUp,
            secondCurrency = exchangeRates.secondCurrency,
            secondCourse = exchangeRates.secondCourse,
            secondIsUp = exchangeRates.secondIsUp,
            thirdCurrency = exchangeRates.thirdCurrency,
            thirdCourse = exchangeRates.thirdCourse,
            thirdIsUp = exchangeRates.thirdIsUp
        )
    }
}

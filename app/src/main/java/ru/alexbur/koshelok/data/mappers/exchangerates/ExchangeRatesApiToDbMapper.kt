package ru.alexbur.koshelok.data.mappers.exchangerates

import ru.alexbur.koshelok.data.db.entity.ExchangeRatesDb
import ru.alexbur.koshelok.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesApiToDbMapper @Inject constructor() :
        (Long, ExchangeRatesApi) -> ExchangeRatesDb {
    override fun invoke(personId: Long, exchangeRates: ExchangeRatesApi): ExchangeRatesDb {
        return ExchangeRatesDb(
            personId = personId,
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

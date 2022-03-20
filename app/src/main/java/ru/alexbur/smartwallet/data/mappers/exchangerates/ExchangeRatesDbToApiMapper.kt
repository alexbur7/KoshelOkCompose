package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesDbToApiMapper @Inject constructor() : (ExchangeRatesDb) -> ExchangeRatesApi {

    override fun invoke(exchangeRates: ExchangeRatesDb): ExchangeRatesApi {
        return ExchangeRatesApi(
            firstCurrency = CurrencyApi(
                name = exchangeRates.firstCurrency,
                course = exchangeRates.firstCourse,
                isUp = exchangeRates.firstIsUp
            ),
            secondCurrency = CurrencyApi(
                name = exchangeRates.secondCurrency,
                course = exchangeRates.secondCourse,
                isUp = exchangeRates.secondIsUp
            ),
            thirdCurrency = CurrencyApi(
                name = exchangeRates.thirdCurrency,
                course = exchangeRates.thirdCourse,
                isUp = exchangeRates.thirdIsUp
            ),
        )
    }
}

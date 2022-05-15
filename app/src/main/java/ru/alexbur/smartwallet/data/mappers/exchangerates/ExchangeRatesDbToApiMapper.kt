package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import javax.inject.Inject

class ExchangeRatesDbToApiMapper @Inject constructor(
) : (ExchangeRatesDb) -> ExchangeRatesApi {

    override fun invoke(exchangeRates: ExchangeRatesDb): ExchangeRatesApi {
        return ExchangeRatesApi(
            firstCurrency = CurrencyApi(
                currencyId = 0,
                name = exchangeRates.firstCurrency,
                course = exchangeRates.firstCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.firstIsUp
            ),
            secondCurrency = CurrencyApi(
                currencyId = 1,
                name = exchangeRates.secondCurrency,
                course = exchangeRates.secondCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.secondIsUp
            ),
            thirdCurrency = CurrencyApi(
                currencyId = 2,
                name = exchangeRates.thirdCurrency,
                course = exchangeRates.thirdCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.thirdIsUp
            ),
        )
    }
}

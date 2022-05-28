package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class ExchangeRatesDbToApiMapper @Inject constructor(
) : (ExchangeRatesDb) -> List<CurrencyApi> {

    override fun invoke(exchangeRates: ExchangeRatesDb): List<CurrencyApi> {
        return listOf(
            CurrencyApi(
                currencyId = 0,
                name = exchangeRates.firstCurrency,
                course = exchangeRates.firstCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.firstIsUp
            ),
            CurrencyApi(
                currencyId = 1,
                name = exchangeRates.secondCurrency,
                course = exchangeRates.secondCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.secondIsUp
            ),
            CurrencyApi(
                currencyId = 2,
                name = exchangeRates.thirdCurrency,
                course = exchangeRates.thirdCourse,
                fullName = "",
                fullListName = "",
                icon = "",
                isUp = exchangeRates.thirdIsUp
            )
        )
    }
}

package ru.alexbur.koshelok.data.mappers.exchangerates

import ru.alexbur.koshelok.data.service.api.ExchangeRatesApi
import ru.alexbur.koshelok.domain.entities.listwallet.ExchangeRatesEntity
import ru.alexbur.koshelok.domain.enums.Currency
import javax.inject.Inject

class ExchangeRatesApiToExchangeRatesEntityMapper @Inject constructor() :
        (ExchangeRatesApi) -> ExchangeRatesEntity {

    override operator fun invoke(exchangeRatesApi: ExchangeRatesApi) =
        ExchangeRatesEntity(
            firstCurrency = Currency.valueOf(exchangeRatesApi.firstCurrency),
            firstCourse = exchangeRatesApi.firstCourse,
            firstIsUp = exchangeRatesApi.firstIsUp,
            secondCurrency = Currency.valueOf(exchangeRatesApi.secondCurrency),
            secondCourse = exchangeRatesApi.secondCourse,
            secondIsUp = exchangeRatesApi.secondIsUp,
            thirdCurrency = Currency.valueOf(exchangeRatesApi.thirdCurrency),
            thirdCourse = exchangeRatesApi.thirdCourse,
            thirdIsUp = exchangeRatesApi.thirdIsUp
        )
}

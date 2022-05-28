package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.domain.entities.listwallet.ExchangeRatesEntity
import javax.inject.Inject

class ExchangeRatesApiToEntityMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToEntityMapper
) : (List<CurrencyApi>) -> ExchangeRatesEntity {

    override operator fun invoke(exchangeRatesApi: List<CurrencyApi>) =
        ExchangeRatesEntity(
            firstCurrency = currencyMapper(ResponseApi(exchangeRatesApi[0])),
            secondCurrency = currencyMapper(ResponseApi(exchangeRatesApi[1])),
            thirdCurrency = currencyMapper(ResponseApi(exchangeRatesApi[2])),
        )
}

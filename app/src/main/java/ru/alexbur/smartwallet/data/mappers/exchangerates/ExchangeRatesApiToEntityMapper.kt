package ru.alexbur.smartwallet.data.mappers.exchangerates

import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.api.ExchangeRatesApi
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.domain.entities.listwallet.ExchangeRatesEntity
import javax.inject.Inject

class ExchangeRatesApiToEntityMapper @Inject constructor(
    private val currencyMapper: CurrencyApiToEntityMapper
) : (ExchangeRatesApi) -> ExchangeRatesEntity {

    override operator fun invoke(exchangeRatesApi: ExchangeRatesApi) =
        ExchangeRatesEntity(
            firstCurrency = currencyMapper(ResponseApi(exchangeRatesApi.firstCurrency)),
            secondCurrency = currencyMapper(ResponseApi(exchangeRatesApi.secondCurrency)),
            thirdCurrency = currencyMapper(ResponseApi(exchangeRatesApi.thirdCurrency)),
        )
}

package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.domain.entities.listwallet.CurrencyEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import javax.inject.Inject

class CurrencyApiToEntityMapper @Inject constructor(): (CurrencyApi) -> CurrencyEntity {

    override fun invoke(currencyApi: CurrencyApi): CurrencyEntity =
        CurrencyEntity(
            name = Currency.valueOf(currencyApi.name),
            course = currencyApi.course,
            isUp = currencyApi.isUp
        )
}

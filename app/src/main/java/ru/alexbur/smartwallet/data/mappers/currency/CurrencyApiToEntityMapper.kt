package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import javax.inject.Inject

class CurrencyApiToEntityMapper @Inject constructor() : (CurrencyApi) -> CurrencyEntity {

    override fun invoke(currencyApi: CurrencyApi): CurrencyEntity =
        CurrencyEntity(
            id = currencyApi.currencyId,
            name = currencyApi.name,
            course = currencyApi.course,
            fullName = currencyApi.fullName,
            fullListName = currencyApi.fullListName,
            icon = currencyApi.icon,
            isUp = currencyApi.isUp
        )
}

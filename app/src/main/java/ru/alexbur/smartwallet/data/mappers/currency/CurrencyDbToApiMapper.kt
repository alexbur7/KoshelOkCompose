package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class CurrencyDbToApiMapper @Inject constructor() : (CurrencyDb) -> CurrencyApi {

    override fun invoke(currency: CurrencyDb): CurrencyApi {
        return CurrencyApi(
            currencyId = currency.currencyId,
            name = currency.name,
            course = currency.course,
            fullName = currency.fullName,
            fullListName = currency.fullListName,
            icon = currency.icon,
            isUp = currency.isUp
        )
    }
}
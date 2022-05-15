package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class CurrencyApiToDbMapper @Inject constructor() : (CurrencyApi) -> CurrencyDb {
    override fun invoke(currency: CurrencyApi): CurrencyDb {
       return CurrencyDb(
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
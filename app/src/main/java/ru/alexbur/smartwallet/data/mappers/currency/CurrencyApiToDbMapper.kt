package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class CurrencyApiToDbMapper @Inject constructor() : (CurrencyApi) -> CurrencyDb {
    override fun invoke(currency: CurrencyApi): CurrencyDb {
       return CurrencyDb(
           currencyId = currency.currencyId ?: 0,
           name = currency.name,
           course = currency.course.defaultMoney(),
           fullName = currency.fullName.orEmpty(),
           fullListName = currency.fullListName.orEmpty(),
           icon = currency.icon.orEmpty(),
           isUp = currency.isUp
       )
    }
}
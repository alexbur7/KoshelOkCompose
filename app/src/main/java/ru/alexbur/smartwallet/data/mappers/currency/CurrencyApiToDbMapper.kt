package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class CurrencyApiToDbMapper @Inject constructor() : (CurrencyApi) -> CurrencyDb {

    override fun invoke(currencyApi: CurrencyApi): CurrencyDb =
        CurrencyDb(
            name = currencyApi.name,
            course = currencyApi.course,
            isUp = currencyApi.isUp
        )
}

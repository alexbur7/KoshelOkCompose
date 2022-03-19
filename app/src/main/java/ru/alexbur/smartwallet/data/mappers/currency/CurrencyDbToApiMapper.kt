package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import javax.inject.Inject

class CurrencyDbToApiMapper @Inject constructor() : (CurrencyDb) -> CurrencyApi {

    override fun invoke(currencyDb: CurrencyDb): CurrencyApi =
        CurrencyApi(
            name = currencyDb.name,
            course = currencyDb.course,
            isUp = currencyDb.isUp
        )
}

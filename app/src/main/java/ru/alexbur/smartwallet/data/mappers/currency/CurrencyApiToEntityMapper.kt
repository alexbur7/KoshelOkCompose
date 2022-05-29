package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.extentions.defaultMoney
import ru.alexbur.smartwallet.data.service.api.CurrencyApi
import ru.alexbur.smartwallet.data.service.api.ResponseApi
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import javax.inject.Inject

class CurrencyApiToEntityMapper @Inject constructor() :
        (ResponseApi<CurrencyApi>) -> CurrencyEntity {

    override fun invoke(currencyApi: ResponseApi<CurrencyApi>): CurrencyEntity =
        CurrencyEntity(
            id = currencyApi.result.currencyId,
            name = currencyApi.result.name,
            course = currencyApi.result.course.defaultMoney(),
            fullName = currencyApi.result.fullName.orEmpty(),
            fullListName = currencyApi.result.fullListName.orEmpty(),
            icon = currencyApi.result.icon.orEmpty(),
            isUp = currencyApi.result.isUp
        )
}

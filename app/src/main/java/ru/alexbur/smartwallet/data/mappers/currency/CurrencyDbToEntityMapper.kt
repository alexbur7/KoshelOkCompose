package ru.alexbur.smartwallet.data.mappers.currency

import ru.alexbur.smartwallet.data.db.entity.CurrencyDb
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import javax.inject.Inject

class CurrencyDbToEntityMapper @Inject constructor() : (CurrencyDb) -> CurrencyEntity {
    override fun invoke(currency: CurrencyDb): CurrencyEntity {
        return CurrencyEntity(
            id = currency.currencyId,
            name = currency.name,
            course = currency.course,
            fullName = currency.fullName,
            fullListName = currency.fullListName,
            icon = currency.icon,
            isUp = currency.isUp
        )
    }
}
package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.mappers.currency.CurrencyApiToEntityMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.repositories.LoadCurrenciesRepository
import javax.inject.Inject

class LoadCurrenciesRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val currencyMapper: CurrencyApiToEntityMapper
) : LoadCurrenciesRepository {

    override suspend fun getCurrencies(): Result<List<CurrencyEntity>> {
        return runCatching {
            appService.getCurrencies().map(currencyMapper)
        }
    }
}
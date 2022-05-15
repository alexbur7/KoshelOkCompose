package ru.alexbur.smartwallet.domain.repositories

import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity

interface LoadCurrenciesRepository {

    suspend fun getCurrencies(): Result<List<CurrencyEntity>>
}
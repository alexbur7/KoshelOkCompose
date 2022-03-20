package ru.alexbur.smartwallet.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.domain.repositories.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor() : CurrencyRepository {
    override val _currentCurrency: MutableStateFlow<Currency> = MutableStateFlow(Currency.RUB)

}
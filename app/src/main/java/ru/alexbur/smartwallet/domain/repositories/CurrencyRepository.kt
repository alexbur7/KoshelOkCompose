package ru.alexbur.smartwallet.domain.repositories

import kotlinx.coroutines.flow.MutableStateFlow
import ru.alexbur.smartwallet.domain.enums.Currency

interface CurrencyRepository {
    val _currentCurrency: MutableStateFlow<Currency>
}
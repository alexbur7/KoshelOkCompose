package ru.alexbur.smartwallet.ui.listcurrency

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.domain.repositories.CurrencyRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : BaseViewModel<CurrenciesViewModel.Event>() {

    val currentCurrency: StateFlow<Currency>
        get() = currencyRepository._currentCurrency.asStateFlow()

    val currencies: StateFlow<List<Currency>> =
        MutableStateFlow(Currency.values().toList()).asStateFlow()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.ChooseCurrency -> {
                chooseCurrency(event.currency)
            }
        }
    }

    private fun chooseCurrency(currency: Currency) = viewModelScope.launch {
        currencyRepository._currentCurrency.emit(currency)
    }

    sealed class Event : BaseEvent() {
        class ChooseCurrency(val currency: Currency) : Event()
    }
}
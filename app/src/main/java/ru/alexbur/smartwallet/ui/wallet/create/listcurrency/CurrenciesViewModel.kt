package ru.alexbur.smartwallet.ui.wallet.create.listcurrency

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.CurrencyScreenType
import ru.alexbur.smartwallet.domain.repositories.LoadCurrenciesRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val loadCurrenciesRepository: LoadCurrenciesRepository,
    private val savingDataManager: SavingDataManager
) : BaseViewModel<CurrenciesViewModel.Event>() {

    val createWalletFlow: StateFlow<CreateWalletEntity> =
        savingDataManager.createWalletFlow.asStateFlow()

    val createTransactionEntity: StateFlow<TransactionEntity?> =
        savingDataManager.createTransactionFlow.asStateFlow()

    val editTransactionFlow: StateFlow<TransactionEntity?> =
        savingDataManager.editTransactionFlow.asStateFlow()

    val editWalletFlow: StateFlow<CreateWalletEntity?> =
        savingDataManager.editWalletFlow.asStateFlow()

    private val _currencies = MutableStateFlow(emptyList<CurrencyEntity>())

    val currencies: StateFlow<List<CurrencyEntity>> = _currencies.asStateFlow()

    init {
        viewModelScope.launch {
            loadCurrenciesRepository.getCurrencies().onSuccess {
                _currencies.emit(it)
            }
        }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.ChooseCurrency -> {
                when (event.createTypeScreen) {
                    CurrencyScreenType.CREATE_WALLET_SCREEN -> chooseWalletCurrency(event.currency)
                    CurrencyScreenType.CREATE_TRANSACTION_SCREEN -> chooseTransactionCurrency(event.currency)
                    CurrencyScreenType.EDIT_TRANSACTION_SCREEN -> chooseEditTransactionCurrency(
                        event.currency
                    )
                    CurrencyScreenType.EDIT_WALLET_SCREEN -> chooseEditWalletCurrency(event.currency)
                }
            }
        }
    }

    private fun chooseWalletCurrency(currency: CurrencyEntity) = viewModelScope.launch {
        savingDataManager.createWalletFlow.update {
            it.copy(currency = currency)
        }
    }

    private fun chooseTransactionCurrency(currency: CurrencyEntity) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(
                currency = currency
            )
        )
    }

    private fun chooseEditWalletCurrency(currency: CurrencyEntity) = viewModelScope.launch {
        savingDataManager.editWalletFlow.update {
            it?.copy(currency = currency)
        }
    }

    private fun chooseEditTransactionCurrency(currency: CurrencyEntity) = viewModelScope.launch {
        savingDataManager.editTransactionFlow.update {
            it?.copy(currency = currency)
        }
    }

    sealed class Event : BaseEvent() {
        class ChooseCurrency(
            val createTypeScreen: CurrencyScreenType,
            val currency: CurrencyEntity
        ) : Event()
    }
}
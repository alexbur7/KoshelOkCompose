package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.CreateCategoryRepository
import ru.alexbur.smartwallet.domain.repositories.CreateTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.CreateWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class NavBarViewModel @Inject constructor(
    private val walletRepository: CreateWalletRepository,
    private val transactionRepository: CreateTransactionRepository,
    private val createCategoryRepository: CreateCategoryRepository,
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler
) : BaseViewModel<NavBarViewModel.Event>() {

    val loadingState: Flow<LoadingState>
        get() = _loadStateData.asStateFlow().onEach { delay(300) }
    val errorState: StateFlow<String>
        get() = _errorData.asStateFlow()
    val walletIdData: StateFlow<Long>
        get() = savingDataManager.walletIdFlow

    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)
    private val _errorData = MutableStateFlow("")

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateWalletStarted -> {
                operationWithWallet()
            }
            is Event.CreateTransactionStarted -> {
                operationWithTransaction()
            }
            is Event.CreateCategoryStarted -> {
                createCategory()
            }
            is Event.CreateDataFailed -> {
                failedCreateItem(error = event.error)
            }
            is Event.SucceedOperation -> {
                succeedOperation()
            }
        }
    }

    private fun operationWithWallet() = viewModelScope.launch {
        val wallet = savingDataManager.editWalletFlow.value ?: run {
            createWallet()
            return@launch
        }
        editWallet(wallet)
    }

    private fun operationWithTransaction() = viewModelScope.launch {
        val transaction = savingDataManager.editTransactionFLow.value ?: run {
            createTransaction()
            return@launch
        }
        editTransaction(transaction)
    }

    private suspend fun createWallet() {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        walletRepository.createWallet(savingDataManager.createWalletFlow.value)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun createTransaction() = viewModelScope.launch {
        val transaction = savingDataManager.createTransactionFlow.value ?: return@launch
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        transactionRepository.createTransaction(transactionEntity = transaction)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun createCategory() = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        createCategoryRepository.createCategory(savingDataManager.createCategoryFlow.value)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private suspend fun editWallet(wallet: WalletEntity) {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        walletRepository.editWallet(wallet)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private suspend fun editTransaction(transaction: TransactionEntity) {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        transactionRepository.editTransaction(transaction)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun failedCreateItem(error: String) = viewModelScope.launch {
        _errorData.emit(error)
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedOperation() = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event : BaseEvent() {
        object CreateWalletStarted : Event()
        object CreateTransactionStarted : Event()
        object CreateCategoryStarted : Event()
        class CreateDataFailed(val error: String) : Event()
        object SucceedOperation : Event()
    }
}
package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    val loadingState: StateFlow<LoadingState>
        get() = _loadingState.asStateFlow()
    val walletIdData: StateFlow<Long>
        get() = savingDataManager.walletIdFlow

    private val _loadingState = MutableStateFlow(LoadingState.LOAD_DEFAULT)

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
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        walletRepository.createWallet(savingDataManager.createWalletFlow.value)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun createTransaction() = viewModelScope.launch {
        val transaction = savingDataManager.createTransactionFlow.value ?: return@launch
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        transactionRepository.createTransaction(transactionEntity = transaction)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun createCategory() = viewModelScope.launch {
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        createCategoryRepository.createCategory(savingDataManager.createCategoryFlow.value)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private suspend fun editWallet(wallet: WalletEntity) {
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        walletRepository.editWallet(wallet)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private suspend fun editTransaction(transaction: TransactionEntity) {
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        transactionRepository.editTransaction(transaction)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun failedCreateItem(error: String) = viewModelScope.launch {
        savingDataManager.snackBarMessageFlow.emit(error)
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_FAILED)
        _loadingState.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedOperation() = viewModelScope.launch {
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_SUCCEED)
        _loadingState.emit(LoadingState.LOAD_SUCCEED)
        savingDataManager.snackBarMessageFlow.emit(errorHandler.succeedOperation())
    }

    sealed class Event : BaseEvent() {
        object CreateWalletStarted : Event()
        object CreateTransactionStarted : Event()
        object CreateCategoryStarted : Event()
        class CreateDataFailed(val error: String) : Event()
        object SucceedOperation : Event()
    }
}
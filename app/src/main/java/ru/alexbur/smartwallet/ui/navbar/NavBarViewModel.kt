package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
    val openEditScreen: SharedFlow<Boolean?>
        get() = _openEditScreen.asSharedFlow()

    private val _loadingState = MutableStateFlow(LoadingState.LOAD_DEFAULT)
    private val _openEditScreen = MutableSharedFlow<Boolean?>()

    init {
        savingDataManager.editWalletFlow.filterNotNull().onEach {
            _openEditScreen.emit(true)
        }.launchIn(viewModelScope)

        savingDataManager.editTransactionFlow.filterNotNull().onEach {
            _openEditScreen.emit(false)
        }.launchIn(viewModelScope)
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateWalletStarted -> {
                createWallet()
            }
            is Event.EditWalletStarted -> {
                editWallet()
            }
            is Event.CreateTransactionStarted -> {
                createTransaction()
            }
            is Event.EditTransactionStarted -> {
                editTransaction()
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
            is Event.ClearOpenEdit -> {
                clearOpenEdit()
            }
        }
    }

    private fun createWallet() = viewModelScope.launch {
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

    private fun editWallet() = viewModelScope.launch {
        val wallet = savingDataManager.editWalletFlow.value ?: return@launch
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
        _loadingState.emit(LoadingState.LOAD_IN_PROGRESS)
        walletRepository.editWallet(wallet)
            .onSuccess {
                obtainEvent(Event.SucceedOperation)
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(errorHandler.handleError(it)))
            }
    }

    private fun editTransaction() = viewModelScope.launch {
        val transaction = savingDataManager.editTransactionFlow.value ?: return@launch
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
        savingDataManager.editWalletFlow.emit(null)
        savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_SUCCEED)
        _loadingState.emit(LoadingState.LOAD_SUCCEED)
        savingDataManager.snackBarMessageFlow.emit(errorHandler.succeedOperation())
    }

    private fun clearOpenEdit() = viewModelScope.launch {
        _openEditScreen.emit(null)
        savingDataManager.editTransactionFlow.emit(null)
        savingDataManager.editWalletFlow.emit(null)
    }

    sealed class Event : BaseEvent() {
        object CreateWalletStarted : Event()
        object EditWalletStarted : Event()
        object CreateTransactionStarted : Event()
        object EditTransactionStarted : Event()
        object CreateCategoryStarted : Event()
        class CreateDataFailed(val error: String) : Event()
        object SucceedOperation : Event()
        object ClearOpenEdit : Event()
    }
}
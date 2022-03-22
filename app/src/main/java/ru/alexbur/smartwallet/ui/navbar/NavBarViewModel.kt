package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.CreateTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.CreateWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class NavBarViewModel @Inject constructor(
    private val createWalletRepository: CreateWalletRepository,
    private val createTransactionRepository: CreateTransactionRepository,
    private val savingDataManager: SavingDataManager
) : BaseViewModel<NavBarViewModel.Event>() {

    val loadingState: Flow<LoadingState>
        get() = _loadStateData.asStateFlow().onEach { delay(300) }
    val errorState: StateFlow<String>
        get() = _errorData.asStateFlow()
    val walletIdData: Flow<Long>
        get() = savingDataManager.walletFlow.map { it?.id ?: -1L }

    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)
    private val _errorData = MutableStateFlow("")

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateWalletStarted -> {
                createWallet()
            }
            is Event.CreateTransactionStarted -> {
                createTransaction()
            }
            is Event.CreateDataFailed -> {
                failedCreateItem(error = event.error)
            }
            is Event.CreateWalletSucceed -> {
                succeedCreateWallet(wallet = event.wallet)
            }
            is Event.CreateTransactionSucceed -> {
                succeedCreteTransaction(event.transaction)
            }
        }
    }

    private fun createWallet() = viewModelScope.launch {
        val createWallet = savingDataManager.createWalletFlow.value ?: return@launch
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        createWalletRepository.createWallet(createWallet)
            .onSuccess {
                obtainEvent(Event.CreateWalletSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(it.localizedMessage ?: ""))
            }
    }

    private fun createTransaction() = viewModelScope.launch {
        val transaction = savingDataManager.createTransactionFlow.value ?: return@launch
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        createTransactionRepository.createTransaction(transactionEntity = transaction)
            .onSuccess {
                obtainEvent(Event.CreateTransactionSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(it.localizedMessage ?: ""))
            }
    }

    private fun failedCreateItem(error: String) = viewModelScope.launch {
        _errorData.emit(error)
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedCreateWallet(wallet: WalletEntity) = viewModelScope.launch {
        savingDataManager.walletFlow.emit(wallet)
        savingDataManager.createWalletFlow.emit(null)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun succeedCreteTransaction(transaction: DetailWalletItem.Transaction) =
        viewModelScope.launch {
            savingDataManager.transactionFlow.emit(transaction)
            savingDataManager.createTransactionFlow.emit(null)
            _loadStateData.emit(LoadingState.LOAD_SUCCEED)
        }

    sealed class Event : BaseEvent() {
        object CreateWalletStarted : Event()
        object CreateTransactionStarted : Event()
        class CreateDataFailed(val error: String) : Event()
        class CreateWalletSucceed(val wallet: WalletEntity) : Event()
        class CreateTransactionSucceed(val transaction: DetailWalletItem.Transaction) : Event()
    }
}
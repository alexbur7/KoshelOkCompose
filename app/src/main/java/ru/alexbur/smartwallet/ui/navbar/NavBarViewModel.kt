package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
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

    val loadingState: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val createWalletState: StateFlow<CreateWalletEntity>
        get() = savingDataManager.createWalletFlow.asStateFlow()
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateWalletStarted -> {
                createWallet(createWallet = event.createWallet)
            }
            is Event.CreateTransactionStarted -> {
                createTransaction(event.transaction)
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

    private fun createWallet(createWallet: CreateWalletEntity) = viewModelScope.launch {
        createWalletRepository.createWallet(createWallet)
            .onSuccess {
                obtainEvent(Event.CreateWalletSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(it.localizedMessage))
            }
    }

    private fun createTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        createTransactionRepository.createTransaction(transaction)
            .onSuccess {
                obtainEvent(Event.CreateTransactionSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(it.localizedMessage))
            }
    }

    private fun failedCreateItem(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedCreateWallet(wallet: WalletEntity) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun succeedCreteTransaction(transaction: DetailWalletItem.Transaction) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event : BaseEvent() {
        class CreateWalletStarted(val createWallet: CreateWalletEntity) : Event()
        class CreateTransactionStarted(val transaction: TransactionEntity) : Event()
        class CreateDataFailed(val error: String?) : Event()
        class CreateWalletSucceed(val wallet: WalletEntity) : Event()
        class CreateTransactionSucceed(val transaction: DetailWalletItem.Transaction) : Event()
    }
}
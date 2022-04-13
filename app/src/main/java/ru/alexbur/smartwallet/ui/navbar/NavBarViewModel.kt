package ru.alexbur.smartwallet.ui.navbar

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.CreateCategoryRepository
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
    private val createCategoryRepository: CreateCategoryRepository,
    private val savingDataManager: SavingDataManager
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
                createWallet()
            }
            is Event.CreateTransactionStarted -> {
                createTransaction()
            }
            is Event.CreateCategoryStarted -> {
                createCategory()
            }
            is Event.CreateDataFailed -> {
                failedCreateItem(error = event.error)
            }
            is Event.CreateWalletSucceed -> {
                succeedCreateWallet(wallet = event.wallet)
            }
            is Event.CreateTransactionSucceed -> {
                succeedCreateTransaction(event.transaction)
            }
            is Event.CreateCategorySucceed -> {
                succeedCreateCategory(event.category)
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

    private fun createCategory() = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        createCategoryRepository.createCategory(savingDataManager.createCategoryFlow.value)
            .onSuccess {
                obtainEvent(Event.CreateCategorySucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateDataFailed(it.localizedMessage ?: ""))
            }
    }

    private fun failedCreateItem(error: String) = viewModelScope.launch {
        _errorData.emit(error)
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedCreateWallet(wallet: WalletEntity) = viewModelScope.launch {
        savingDataManager.walletIdFlow.emit(wallet.id)
        savingDataManager.createWalletFlow.emit(null)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun succeedCreateTransaction(transaction: DetailWalletItem.Transaction) =
        viewModelScope.launch {
            savingDataManager.transactionFlow.emit(transaction)
            savingDataManager.createTransactionFlow.emit(null)
            _loadStateData.emit(LoadingState.LOAD_SUCCEED)
        }

    private fun succeedCreateCategory(category: CategoryEntity) = viewModelScope.launch {
        savingDataManager.categoriesFlow.emit(savingDataManager.categoriesFlow.value + category)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
        savingDataManager.createCategoryFlow.emit(CategoryEntity.defaultCategory)
    }

    sealed class Event : BaseEvent() {
        object CreateWalletStarted : Event()
        object CreateTransactionStarted : Event()
        object CreateCategoryStarted : Event()
        class CreateDataFailed(val error: String) : Event()
        class CreateWalletSucceed(val wallet: WalletEntity) : Event()
        class CreateTransactionSucceed(val transaction: DetailWalletItem.Transaction) : Event()
        class CreateCategorySucceed(val category: CategoryEntity) : Event()
    }
}
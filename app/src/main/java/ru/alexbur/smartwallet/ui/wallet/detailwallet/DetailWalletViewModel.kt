package ru.alexbur.smartwallet.ui.wallet.detailwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel

class DetailWalletViewModel @AssistedInject constructor(
    @Assisted
    private val walletId: Long,
    private val detailWalletRepository: DetailWalletRepository,
    private val deleteTransactionRepository: DeleteTransactionRepository,
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler
) : BaseViewModel<DetailWalletViewModel.Event>() {

    val walletsData: StateFlow<List<WalletEntity>>
        get() = _detailWalletData.asStateFlow()
    val transitionsData: StateFlow<List<DetailWalletItem>>
        get() = _transitionsData.asStateFlow()
    val loadStateData: SharedFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val errorMessage: StateFlow<String>
        get() = _errorMessage.asStateFlow()

    val positionWallet: Int
        get() = walletsData.value.indexOfFirst { it.id == walletId }

    private val _detailWalletData = MutableStateFlow(WalletEntity.shimmerData)
    private val _transitionsData =
        MutableStateFlow(DetailWalletItem.shimmerData)
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)
    private val _errorMessage = MutableStateFlow("")

    init {
        viewModelScope.launch {
            obtainEvent(Event.OnLoadingStarted(walletId = walletId))
        }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading(walletId = event.walletId)
            }
            is Event.OnLoadingDBSucceed -> {
                succeedDbLoading(list = event.data, transactions = event.transactions)
            }
            is Event.OnLoadingNetworkStarted -> {
                startNetworkLoading(walletId = event.walletId)
            }
            is Event.OnLoadingFailed -> {
                failLoading(error = event.error)
            }
            is Event.OnLoadingNetworkSucceed -> {
                succeedNetworkLoading(list = event.data, transactions = event.transactions)
            }
            is Event.OnLoadingTransactionStarted -> {
                startDBTransactionLoading(event.walletId)
            }
            is Event.OnLoadingTransactionDBSucceed -> {
                succeedDBTransactionLoading(event.transactions, event.walletId)
            }
            is Event.OnLoadingTransactionNetworkStarted -> {
                startedNetworkTransactionLoading(event.walletId)
            }
            is Event.OnLoadingTransactionServerSucceed -> {
                succeedNetworkTransactionLoading(event.transactions)
            }
            is Event.DeleteTransaction -> {
                deleteTransaction(event.transaction)
            }
        }
    }

    private fun startLoading(walletId: Long) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)

        val data = detailWalletRepository.getDbWalletData().getOrNull()
        val transactionData = detailWalletRepository.getDbTransactionsData(walletId).getOrNull()
        obtainEvent(
            Event.OnLoadingDBSucceed(
                if (!data.isNullOrEmpty()) data else WalletEntity.shimmerData,
                transactionData ?: DetailWalletItem.shimmerData
            )
        )
    }

    private fun succeedDbLoading(list: List<WalletEntity>, transactions: List<DetailWalletItem>) =
        viewModelScope.launch {
            _detailWalletData.emit(list)
            _transitionsData.emit(transactions)
            obtainEvent(Event.OnLoadingNetworkStarted(walletId = walletId))
        }

    private fun startNetworkLoading(walletId: Long) = viewModelScope.launch {
        val transactions =
            detailWalletRepository.getServerTransactionsData(walletId = walletId).getOrNull()
        detailWalletRepository.getServerWalletsData()
            .onSuccess {
                obtainEvent(Event.OnLoadingNetworkSucceed(it, transactions ?: emptyList()))
            }.onFailure {
                obtainEvent(Event.OnLoadingFailed(errorHandler.handleError(it)))
            }
    }

    private fun succeedNetworkLoading(
        list: List<WalletEntity>,
        transactions: List<DetailWalletItem>
    ) = viewModelScope.launch {
        _detailWalletData.emit(list)
        _transitionsData.emit(transactions)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun failLoading(error: String) = viewModelScope.launch {
        _errorMessage.emit(error)
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun startDBTransactionLoading(walletId: Long) = viewModelScope.launch {
        savingDataManager.walletIdFlow.emit(walletId)
        _transitionsData.emit(DetailWalletItem.shimmerData)

        val transactionData = detailWalletRepository.getDbTransactionsData(walletId).getOrNull()
        obtainEvent(
            Event.OnLoadingTransactionDBSucceed(
                walletId = walletId,
                transactions = transactionData ?: DetailWalletItem.shimmerData,
            )
        )
    }

    private fun succeedDBTransactionLoading(data: List<DetailWalletItem>, walletId: Long) =
        viewModelScope.launch {
            _transitionsData.emit(data)
            obtainEvent(Event.OnLoadingTransactionNetworkStarted(walletId = walletId))
        }

    private fun startedNetworkTransactionLoading(walletId: Long) = viewModelScope.launch {
        detailWalletRepository.getServerTransactionsData(walletId = walletId).onSuccess {
            obtainEvent(Event.OnLoadingTransactionServerSucceed(it))
        }.onFailure {
            obtainEvent(Event.OnLoadingFailed(errorHandler.handleError(it)))
        }
    }

    private fun succeedNetworkTransactionLoading(data: List<DetailWalletItem>) =
        viewModelScope.launch {
            _transitionsData.emit(data)
        }

    private fun deleteTransaction(deleteTransaction: DetailWalletItem.Transaction) =
        viewModelScope.launch {
            _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
            deleteTransactionRepository.deleteTransaction(deleteTransaction.id).onSuccess {
                _detailWalletData.emit(_detailWalletData.value.filter { detailWalletItem ->
                    /*if (detailWalletItem is DetailWalletItem.Transaction && it) {
                        detailWalletItem != deleteTransaction
                    } else {
                        true
                    }*/
                    true
                })
                _loadStateData.emit(LoadingState.LOAD_SUCCEED)
            }.onFailure {
                _errorMessage.emit(errorHandler.handleError(it))
                _loadStateData.emit(LoadingState.LOAD_FAILED)
            }
        }

    sealed class Event : BaseEvent() {
        class OnLoadingStarted(val walletId: Long) : Event()
        class OnLoadingNetworkStarted(val walletId: Long) : Event()
        class OnLoadingFailed(val error: String) : Event()
        class OnLoadingDBSucceed(
            val data: List<WalletEntity>,
            val transactions: List<DetailWalletItem>
        ) : Event()

        class OnLoadingNetworkSucceed(
            val data: List<WalletEntity>,
            val transactions: List<DetailWalletItem>
        ) : Event()

        class OnLoadingTransactionStarted(val walletId: Long) : Event()
        class OnLoadingTransactionNetworkStarted(val walletId: Long) : Event()
        class OnLoadingTransactionDBSucceed(
            val walletId: Long,
            val transactions: List<DetailWalletItem>
        ) : Event()

        class OnLoadingTransactionServerSucceed(
            val transactions: List<DetailWalletItem>
        ) : Event()

        class DeleteTransaction(val transaction: DetailWalletItem.Transaction) : Event()
    }

    @AssistedFactory
    interface Factory {
        fun create(
            walletId: Long
        ): DetailWalletViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            walletId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(
                    walletId
                ) as T
            }
        }
    }
}

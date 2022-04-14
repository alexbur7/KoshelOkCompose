package ru.alexbur.smartwallet.ui.filter.transactions

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FilterTransactionsViewModel @Inject constructor(
    private val detailWalletRepository: DetailWalletRepository,
    private val savingDataManager: SavingDataManager
) : BaseViewModel<FilterTransactionsViewModel.Event>() {
    val loadStateData: SharedFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val transitionsData: StateFlow<List<DetailWalletItem>>
        get() = _transitionsData.asStateFlow()

    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_DEFAULT)
    private val _transitionsData = MutableStateFlow(DetailWalletItem.shimmerData)
    private val allTransactions = mutableListOf<DetailWalletItem>()

    init {
        obtainEvent(Event.OnLoadingDBTransactionStarted)
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingDBTransactionStarted -> {
                startDBTransactionLoading()
            }
            is Event.OnLoadingTransactionDBSucceed -> {
                succeedDBTransactionLoading(event.transactions)
            }
            is Event.OnLoadingTransactionNetworkStarted -> {
                startedNetworkTransactionLoading()
            }
            is Event.OnLoadingTransactionServerSucceed -> {
                succeedNetworkTransactionLoading(event.transactions)
            }
            is Event.OnLoadingFailed -> {
                failLoading(event.error)
            }
            is Event.FilterTransaction -> {
                filterTransactions(event.filterText)
            }
        }
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun startDBTransactionLoading() = viewModelScope.launch {
        _transitionsData.emit(DetailWalletItem.shimmerData)
        val walletId = savingDataManager.walletIdFlow.value
        val transactionData = detailWalletRepository.getDbTransactionsData(walletId).getOrNull()
        obtainEvent(
            Event.OnLoadingTransactionDBSucceed(
                transactions = transactionData ?: DetailWalletItem.shimmerData,
            )
        )
    }

    private fun succeedDBTransactionLoading(data: List<DetailWalletItem>) =
        viewModelScope.launch {
            allTransactions.addAll(data)
            _transitionsData.emit(allTransactions)
            obtainEvent(Event.OnLoadingTransactionNetworkStarted)
        }

    private fun startedNetworkTransactionLoading() = viewModelScope.launch {
        detailWalletRepository.getServerTransactionsData(savingDataManager.walletIdFlow.value)
            .onSuccess {
                obtainEvent(Event.OnLoadingTransactionServerSucceed(it))
            }.onFailure {
            obtainEvent(Event.OnLoadingFailed(it.localizedMessage ?: "Error"))
        }
    }

    private fun succeedNetworkTransactionLoading(data: List<DetailWalletItem>) =
        viewModelScope.launch {
            allTransactions.clear()
            allTransactions.addAll(data)
            _transitionsData.emit(allTransactions)
        }


    private fun filterTransactions(filter: String) = viewModelScope.launch {
        _transitionsData.emit(allTransactions.filter { data ->
            if (data is DetailWalletItem.Transaction) {
                data.category.operation.contains(filter)
            } else {
                true
            }
        })
    }

    sealed class Event : BaseEvent() {
        object OnLoadingDBTransactionStarted : Event()
        object OnLoadingTransactionNetworkStarted : Event()
        class OnLoadingTransactionDBSucceed(val transactions: List<DetailWalletItem>) : Event()

        class OnLoadingTransactionServerSucceed(val transactions: List<DetailWalletItem>) : Event()

        class OnLoadingFailed(val error: String?) : Event()

        class FilterTransaction(val filterText: String) : Event()
    }
}
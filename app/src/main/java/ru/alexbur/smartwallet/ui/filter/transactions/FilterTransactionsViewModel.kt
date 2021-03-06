package ru.alexbur.smartwallet.ui.filter.transactions

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FilterTransactionsViewModel @Inject constructor(
    private val detailWalletRepository: DetailWalletRepository,
    private val deleteTransactionRepository: DeleteTransactionRepository,
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler
) : BaseViewModel<FilterTransactionsViewModel.Event>() {
    val transitionsData: StateFlow<List<DetailWalletItem>>
        get() = _transitionsData.asStateFlow()
    val errorMessage: StateFlow<String>
        get() = _errorMessage.asStateFlow()

    private val _transitionsData = MutableStateFlow(DetailWalletItem.shimmerData)
    private val _errorMessage = MutableStateFlow("")
    private val allTransactions = mutableListOf<DetailWalletItem>()

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
            is Event.DeleteTransaction -> {
                deleteTransaction(event.transactionId)
            }
            is Event.EditWallet -> {
                editTransaction(event.transaction)
            }
        }
    }

    private fun failLoading(error: String) = viewModelScope.launch {
        _errorMessage.emit(error)
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
            allTransactions.clear()
            allTransactions.addAll(data)
            _transitionsData.emit(allTransactions.toList())
            obtainEvent(Event.OnLoadingTransactionNetworkStarted)
        }

    private fun startedNetworkTransactionLoading() = viewModelScope.launch {
        detailWalletRepository.getServerTransactionsData(savingDataManager.walletIdFlow.value)
            .onSuccess {
                obtainEvent(Event.OnLoadingTransactionServerSucceed(it))
            }.onFailure {
                obtainEvent(Event.OnLoadingFailed(errorHandler.handleError(it)))
            }
    }

    private fun succeedNetworkTransactionLoading(data: List<DetailWalletItem>) =
        viewModelScope.launch {
            allTransactions.clear()
            allTransactions.addAll(data)
            _transitionsData.emit(allTransactions.toList())
        }


    private fun filterTransactions(filter: String) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            val filtersNameList = allTransactions.filter { data ->
                if (data is DetailWalletItem.Transaction) {
                    data.category.operation.contains(filter, ignoreCase = true)
                } else {
                    true
                }
            }
            _transitionsData.emit(filtersNameList.filterIndexed { index, detailWalletItem ->
                !(detailWalletItem is DetailWalletItem.Day && (index == filtersNameList.lastIndex
                        || filtersNameList[index + 1] is DetailWalletItem.Day))
            })
        }
    }

    private fun deleteTransaction(id: Long) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            deleteTransactionRepository.deleteTransaction(id).onSuccess {
                _transitionsData.update {
                    it.filter { detailWalletItem ->
                        if (detailWalletItem is DetailWalletItem.Transaction) {
                            detailWalletItem.id != id
                        } else {
                            true
                        }
                    }
                }
            }.onFailure {
                _errorMessage.emit(errorHandler.handleError(it))
            }
        }
    }

    private fun editTransaction(transaction: DetailWalletItem.Transaction) = viewModelScope.launch {
        savingDataManager.editTransactionFlow.emit(
            TransactionEntity(
                id = transaction.id,
                idWallet = transaction.walletId,
                sum = transaction.money,
                type = transaction.category.type,
                categoryEntity = transaction.category,
                date = transaction.timeStamp,
                currency = transaction.currency
            )
        )
    }

    sealed class Event : BaseEvent() {
        object OnLoadingDBTransactionStarted : Event()
        object OnLoadingTransactionNetworkStarted : Event()
        class OnLoadingTransactionDBSucceed(val transactions: List<DetailWalletItem>) : Event()

        class OnLoadingTransactionServerSucceed(val transactions: List<DetailWalletItem>) : Event()

        class OnLoadingFailed(val error: String) : Event()

        class FilterTransaction(val filterText: String) : Event()

        class DeleteTransaction(val transactionId: Long) : Event()

        class EditWallet(val transaction: DetailWalletItem.Transaction) : Event()
    }
}
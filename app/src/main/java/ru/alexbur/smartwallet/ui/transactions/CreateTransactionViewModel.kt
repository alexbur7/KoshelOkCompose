package ru.alexbur.smartwallet.ui.transactions

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.CreateTransactionRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

class CreateTransactionViewModel @Inject constructor(
    private val transactionRepository: CreateTransactionRepository,
) : BaseViewModel<CreateTransactionViewModel.Event>() {

    val transactionEntity: StateFlow<DetailWalletItem.Transaction?>
        get() = _transaction.asStateFlow()
    val loadStateData: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()

    private val _transaction = MutableStateFlow<DetailWalletItem.Transaction?>(null)
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateTransactionStarted -> {
                createTransaction(createTransaction = event.transaction)
            }
            is Event.CreateTransactionFailed -> {
                failedCreateTransaction(error = event.error)
            }
            is Event.CreateTransactionSucceed -> {
                succeedCreateTransaction(transaction = event.transaction)
            }
        }
    }

    private fun createTransaction(createTransaction: TransactionEntity) = viewModelScope.launch {
        transactionRepository.createTransaction(createTransaction)
            .onSuccess {
                obtainEvent(Event.CreateTransactionSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateTransactionFailed(it.localizedMessage))
            }
    }

    private fun failedCreateTransaction(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedCreateTransaction(transaction: DetailWalletItem.Transaction) =
        viewModelScope.launch {
            _transaction.emit(transaction)
            _loadStateData.emit(LoadingState.LOAD_SUCCEED)
        }

    sealed class Event : BaseEvent() {
        class CreateTransactionStarted(val transaction: TransactionEntity) : Event()
        class CreateTransactionFailed(val error: String?) : Event()
        class CreateTransactionSucceed(val transaction: DetailWalletItem.Transaction) : Event()
    }
}

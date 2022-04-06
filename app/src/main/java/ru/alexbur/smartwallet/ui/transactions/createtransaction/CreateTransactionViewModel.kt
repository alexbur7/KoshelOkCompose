package ru.alexbur.smartwallet.ui.transactions.createtransaction

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(
    private val savingDataManager: SavingDataManager,
) : BaseViewModel<CreateTransactionViewModel.Event>() {

    val createTransactionFlow: StateFlow<TransactionEntity?>
        get() = savingDataManager.createTransactionFlow.asStateFlow()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.InitCreateTransaction -> {
                initCreateTransaction(event.transaction)
            }
            is Event.UpdateSumTransaction -> {
                updateSumTransaction(event.sum)
            }
            is Event.UpdateTypeOperation -> {
                updateTypeOperation(event.typeOperation)
            }
            is Event.UpdateDate -> {
                updateDate(event.date)
            }
        }
    }

    private fun initCreateTransaction(transaction: TransactionEntity) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(transaction)
    }

    private fun updateSumTransaction(sum: String) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(sum = sum)
        )
    }

    private fun updateTypeOperation(typeOperation: TypeOperation) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(type = typeOperation)
        )
    }

    private fun updateDate(date: Long) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(date = date)
        )
    }

    sealed class Event : BaseEvent() {
        class InitCreateTransaction(val transaction: TransactionEntity) : Event()
        class UpdateSumTransaction(val sum: String) : Event()
        class UpdateTypeOperation(val typeOperation: TypeOperation) : Event()
        class UpdateDate(val date: Long) : Event()
    }
}

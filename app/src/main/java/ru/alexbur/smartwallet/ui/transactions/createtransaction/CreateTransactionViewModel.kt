package ru.alexbur.smartwallet.ui.transactions.createtransaction

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
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

            }
            is Event.UpdateSumTransaction -> {

            }
        }
    }

    sealed class Event : BaseEvent() {
        class InitCreateTransaction(val transaction: TransactionEntity) : Event()
        class UpdateSumTransaction(val sum: String) : Event()
        class UpdateTypeOperation(val typeOperation: TypeOperation) : Event()
    }
}

package ru.alexbur.smartwallet.ui.transactions.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.LoadCategoriesRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateTransactionViewModel @Inject constructor(
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler,
    loadCategoriesRepository: LoadCategoriesRepository
) : BaseViewModel<CreateTransactionViewModel.Event>() {

    val createTransactionFlow: StateFlow<TransactionEntity?>
        get() = savingDataManager.createTransactionFlow.asStateFlow()

    val categoriesFlow: StateFlow<List<CategoryEntity>>
        get() = savingDataManager.categoriesFlow.asStateFlow()

    val errorMessage: SharedFlow<String> = savingDataManager.snackBarMessageFlow.asSharedFlow()
    val loadingStateFlow: SharedFlow<LoadingState> =
        savingDataManager.loadingStateFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_IN_PROGRESS)
            loadCategoriesRepository.getCategories().onSuccess {
                savingDataManager.categoriesFlow.emit(it)
                savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_SUCCEED)
            }.onFailure {
                savingDataManager.snackBarMessageFlow.emit(errorHandler.handleError(it))
                savingDataManager.loadingStateFlow.emit(LoadingState.LOAD_FAILED)
            }
        }
    }

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
        savingDataManager.createTransactionFlow.emit(transaction.copy(idWallet = savingDataManager.walletIdFlow.value))
    }

    private fun updateSumTransaction(sum: String) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.update {
            it?.copy(sum = sum)
        }
    }

    private fun updateTypeOperation(typeOperation: TypeOperation) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.update {
            it?.copy(
                type = typeOperation,
                categoryEntity = categoriesFlow.value.first { category -> category.type == typeOperation })
        }
    }

    private fun updateDate(date: Long) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.update {
            it?.copy(date = date)
        }
    }

    sealed class Event : BaseEvent() {
        class InitCreateTransaction(val transaction: TransactionEntity) : Event()
        class UpdateSumTransaction(val sum: String) : Event()
        class UpdateTypeOperation(val typeOperation: TypeOperation) : Event()
        class UpdateDate(val date: Long) : Event()
    }
}

package ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel

class CategoriesViewModel @AssistedInject constructor(
    @Assisted
    private val type: TypeOperation,
    private val savingDataManager: SavingDataManager
) : BaseViewModel<CategoriesViewModel.Event>() {

    val listCategory: Flow<List<CategoryEntity>>
        get() = savingDataManager.categoriesFlow.asStateFlow()
            .map { it.filter { category -> category.type == type } }

    val chooseCategory: StateFlow<TransactionEntity?>
        get() = savingDataManager.createTransactionFlow.asStateFlow()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.ChooseCategory -> chooseCategory(event.categoryEntity)
        }
    }

    private fun chooseCategory(categoryEntity: CategoryEntity) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(categoryEntity = categoryEntity)
        )
        savingDataManager.editTransactionFlow.update {
            it?.copy(categoryEntity = categoryEntity)
        }
    }

    sealed class Event : BaseEvent() {
        class ChooseCategory(val categoryEntity: CategoryEntity) : Event()
    }

    @AssistedFactory
    interface Factory {
        fun create(typeOperation: TypeOperation): CategoriesViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            type: TypeOperation
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(type) as T
            }
        }
    }
}

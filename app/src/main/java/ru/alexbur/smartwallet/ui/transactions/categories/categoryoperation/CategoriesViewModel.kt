package ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
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

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.ChooseCategory -> chooseCategory(event.categoryEntity)
        }
    }

    private fun chooseCategory(categoryEntity: CategoryEntity) = viewModelScope.launch {
        savingDataManager.createTransactionFlow.emit(
            savingDataManager.createTransactionFlow.value?.copy(categoryEntity = categoryEntity)
        )
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

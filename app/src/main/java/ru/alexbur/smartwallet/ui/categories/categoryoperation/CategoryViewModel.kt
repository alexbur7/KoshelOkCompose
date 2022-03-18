package ru.alexbur.smartwallet.ui.categories.categoryoperation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.wallet.TransactionEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.LoadCategoriesRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel

class CategoryViewModel @AssistedInject constructor(
    @Assisted
    type: Int,
    private val loadCategories: LoadCategoriesRepository
) : BaseViewModel<CategoryViewModel.Event>() {

    val listCategory: StateFlow<List<CategoryEntity>>
        get() = _listCategories.asStateFlow()
    val transaction: StateFlow<TransactionEntity?>
        get() = _transactionFlow.asStateFlow()
    val productsLoadState: StateFlow<LoadingState>
        get() = _productsLoadState.asStateFlow()

    private val _listCategories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    private val _transactionFlow = MutableStateFlow<TransactionEntity?>(null)
    private val _productsLoadState = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    init {
        viewModelScope.launch { obtainEvent(Event.OnLoadingStarted(type)) }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading(type = event.type)
            }
            is Event.OnLoadingFailed -> {
                failLoading(error = event.error)
            }
            is Event.OnLoadingSucceed -> {
                succeedLoading(list = event.data)
            }
        }
    }

    private fun startLoading(type: Int) = viewModelScope.launch {
        _productsLoadState.emit(LoadingState.LOAD_IN_PROGRESS)

        loadCategories.getCategories(type)
            .onSuccess {
                obtainEvent(Event.OnLoadingSucceed(it))
            }
            .onFailure {
                obtainEvent(Event.OnLoadingFailed(it.localizedMessage))
            }
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _productsLoadState.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedLoading(list: List<CategoryEntity>) = viewModelScope.launch {
        _listCategories.emit(list)
        _productsLoadState.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event : BaseEvent() {
        class OnLoadingStarted(val type: Int) : Event()
        class OnLoadingFailed(val error: String?) : Event()
        class OnLoadingSucceed(val data: List<CategoryEntity>) : Event()
    }

    @AssistedFactory
    interface Factory {
        fun create(type: Int): CategoryViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            type: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(type) as T
            }
        }
    }
}

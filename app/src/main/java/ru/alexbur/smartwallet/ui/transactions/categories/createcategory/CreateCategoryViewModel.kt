package ru.alexbur.smartwallet.ui.transactions.categories.createcategory

import android.graphics.Color
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.IconEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.CreateCategoryRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val createCategoryRepository: CreateCategoryRepository,
) : BaseViewModel<CreateCategoryViewModel.Event>() {


    val listIconModel: StateFlow<List<IconEntity>>
        get() = _listIconModel.asStateFlow()
    val loadStateData: StateFlow<LoadingState>
        get() = _loadState.asStateFlow()

    private val _listIconModel = MutableStateFlow<List<IconEntity>>(emptyList())
    private val _loadState = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    init {
        viewModelScope.launch {
            val listIcon = mutableListOf<IconEntity>()
            for (iconId in 0..COUNT_ICONS) {
                listIcon.add(
                    IconEntity(
                        iconId,
                        IconConverter().convertNumberToDrawableId(iconId),
                        Color.parseColor("#5833EE"),
                        false
                    )
                )
            }
            _listIconModel.emit(listIcon)
        }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading(event.category)
            }
            is Event.OnLoadingFailed -> {
                failLoading(event.error)
            }
            is Event.OnLoadingSucceed -> {
                succeedLoading()
            }
        }
    }

    private fun startLoading(category: CategoryEntity) = viewModelScope.launch {
        _loadState.emit(LoadingState.LOAD_IN_PROGRESS)
        createCategoryRepository.createCategory(category).onSuccess {
            obtainEvent(event = Event.OnLoadingSucceed)
        }.onFailure {
            obtainEvent(event = Event.OnLoadingFailed(it.localizedMessage))
        }
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _loadState.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedLoading() = viewModelScope.launch {
        _loadState.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event : BaseEvent() {
        class OnLoadingStarted(val category: CategoryEntity) : Event()
        class OnLoadingFailed(val error: String?) : Event()
        object OnLoadingSucceed : Event()
    }

    companion object {
        private const val COUNT_ICONS = 29
    }
}

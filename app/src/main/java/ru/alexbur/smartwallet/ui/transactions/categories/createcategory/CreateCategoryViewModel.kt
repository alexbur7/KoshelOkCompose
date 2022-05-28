package ru.alexbur.smartwallet.ui.transactions.categories.createcategory

import androidx.annotation.DrawableRes
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.data.utils.IconConverter
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.IconEntity
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateCategoryViewModel @Inject constructor(
    private val savingDataManager: SavingDataManager
) : BaseViewModel<CreateCategoryViewModel.Event>() {


    val createCategoryFlow: StateFlow<CategoryEntity>
        get() = savingDataManager.createCategoryFlow.asStateFlow()

    val listIconModel: StateFlow<List<IconEntity>>
        get() = _listIconModel.asStateFlow()

    val snackBarMessage: SharedFlow<String> = savingDataManager.snackBarMessageFlow.asSharedFlow()

    private val _listIconModel = MutableStateFlow<List<IconEntity>>(emptyList())

    init {
        viewModelScope.launch {
            val listIcon = mutableListOf<IconEntity>()
            for (iconId in 0..COUNT_ICONS) {
                listIcon.add(
                    IconEntity(
                        iconId,
                        IconConverter().convertNumberToDrawableId(iconId),
                        false
                    )
                )
            }
            _listIconModel.emit(listIcon)
        }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.UpdateNameCategory -> {
                updateNameCategory(event.name)
            }
            is Event.UpdateTypeOperation -> {
                updateTypeOperation(event.typeOperation)
            }
            is Event.UpdateIconCategory -> {
                updateIconCategory(event.idIcon)
            }
        }
    }

    private fun updateNameCategory(name: String) = viewModelScope.launch {
        savingDataManager.createCategoryFlow.emit(
            savingDataManager.createCategoryFlow.value.copy(
                operation = name
            )
        )
    }

    private fun updateTypeOperation(typeOperation: TypeOperation) = viewModelScope.launch {
        savingDataManager.createCategoryFlow.emit(
            savingDataManager.createCategoryFlow.value.copy(
                type = typeOperation
            )
        )
    }

    private fun updateIconCategory(idIcon: Int) = viewModelScope.launch {
        savingDataManager.createCategoryFlow.emit(
            savingDataManager.createCategoryFlow.value.copy(
                iconId = idIcon
            )
        )
    }

    sealed class Event : BaseEvent() {
        class UpdateNameCategory(val name: String) : Event()
        class UpdateTypeOperation(val typeOperation: TypeOperation) : Event()
        class UpdateIconCategory(@DrawableRes val idIcon: Int) : Event()
    }

    companion object {
        private const val COUNT_ICONS = 29
    }
}

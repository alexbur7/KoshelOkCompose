package ru.alexbur.smartwallet.ui.wallet.create

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val savingDataManager: SavingDataManager
) : BaseViewModel<CreateWalletViewModel.Event>() {

    val createWalletFlow: StateFlow<CreateWalletEntity> =
        savingDataManager.createWalletFlow.asStateFlow()

    val errorMessageFlow: SharedFlow<String> = savingDataManager.snackBarMessageFlow.asSharedFlow()

    val loadingState: SharedFlow<LoadingState> = savingDataManager.loadingStateFlow.asSharedFlow()

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.UpdateNameWallet -> {
                updateNameWallet(name = event.name)
            }
            is Event.UpdateLimitWallet -> {
                updateLimitWallet(limit = event.limit)
            }
        }
    }

    private fun updateNameWallet(name: String) = viewModelScope.launch {
        savingDataManager.createWalletFlow.update {
            it.copy(name = name)
        }
    }

    private fun updateLimitWallet(limit: String) = viewModelScope.launch {
        savingDataManager.createWalletFlow.update {
            it.copy(limit = limit.ifEmpty { null })
        }
    }

    sealed class Event : BaseEvent() {
        class UpdateNameWallet(val name: String) : Event()
        class UpdateLimitWallet(val limit: String) : Event()
    }
}

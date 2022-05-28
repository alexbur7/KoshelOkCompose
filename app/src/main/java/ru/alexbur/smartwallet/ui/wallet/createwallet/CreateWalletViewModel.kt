package ru.alexbur.smartwallet.ui.wallet.createwallet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val savingDataManager: SavingDataManager
) : BaseViewModel<CreateWalletViewModel.Event>() {

    val createWalletFlow: StateFlow<CreateWalletEntity>
        get() = savingDataManager.createWalletFlow.asStateFlow()

    val errorMessageFlow: SharedFlow<String> = savingDataManager.snackBarMessageFlow.asSharedFlow()

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
        savingDataManager.createWalletFlow.emit(savingDataManager.createWalletFlow.value.copy(name = name))
    }

    private fun updateLimitWallet(limit: String?) = viewModelScope.launch {
        savingDataManager.createWalletFlow.emit(savingDataManager.createWalletFlow.value.copy(limit = limit))
    }

    sealed class Event : BaseEvent() {
        class UpdateNameWallet(val name: String) : Event()
        class UpdateLimitWallet(val limit: String?) : Event()
    }
}

package ru.alexbur.smartwallet.ui.wallet

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.CreateWalletRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

class WalletViewModel @Inject constructor(
    private val createWalletRepository: CreateWalletRepository,
) : BaseViewModel<WalletViewModel.Event>() {

    val walletData: StateFlow<WalletEntity?>
        get() = _walletData.asStateFlow()
    val loadingState: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    private val _walletData = MutableStateFlow<WalletEntity?>(null)
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.CreateWalletStarted -> {
                createWallet(createWallet = event.createWalletEntity)
            }
            is Event.CreateWalletFailed -> {
                failedCreateWallet(error = event.error)
            }
            is Event.CreateWalletSucceed -> {
                succeedCreateWallet(wallet = event.wallet)
            }
        }
    }

    private fun createWallet(createWallet: CreateWalletEntity) = viewModelScope.launch {
        createWalletRepository.createWallet(createWallet)
            .onSuccess {
                obtainEvent(Event.CreateWalletSucceed(it))
            }.onFailure {
                obtainEvent(Event.CreateWalletFailed(it.localizedMessage))
            }
    }

    private fun failedCreateWallet(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun succeedCreateWallet(wallet: WalletEntity) = viewModelScope.launch {
        _walletData.emit(wallet)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event : BaseEvent() {
        class CreateWalletStarted(val createWalletEntity: CreateWalletEntity) : Event()
        class CreateWalletFailed(val error: String?) : Event()
        class CreateWalletSucceed(val wallet: WalletEntity) : Event()
    }
}

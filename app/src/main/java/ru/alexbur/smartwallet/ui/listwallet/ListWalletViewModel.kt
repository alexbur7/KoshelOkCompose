package ru.alexbur.smartwallet.ui.listwallet

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.DeleteWalletRepository
import ru.alexbur.smartwallet.domain.repositories.MainScreenRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

class ListWalletViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository,
    private val deleteWalletRepository: DeleteWalletRepository,
) : BaseViewModel<ListWalletViewModel.Event>() {

    val loadStateData: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val mainScreenData: StateFlow<MainScreenDataEntity>
        get() = _mainScreenData.asStateFlow()

    private val _mainScreenData = MutableStateFlow(MainScreenDataEntity.shimmerData)
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    init {
        obtainEvent(Event.OnLoadingStarted)
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading()
            }
            is Event.OnLoadingDBSucceed -> {
                succeedDbLoading(data = event.data)
            }
            is Event.OnLoadingNetworkStarted -> {
                startNetworkLoading()
            }
            is Event.OnLoadingNetworkSucceed -> {
                succeedNetworkLoading(data = event.data)
            }
            is Event.OnLoadingFailed -> {
                failLoading(error = event.error)
            }
            is Event.DeleteWallet -> {
                deleteWallet(event.walletId)
            }
        }
    }

    private fun startLoading() = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        val data = mainScreenRepository.getDbMainScreenData().getOrNull()
        obtainEvent(Event.OnLoadingDBSucceed(data ?: MainScreenDataEntity.shimmerData))
    }

    private fun succeedDbLoading(data: MainScreenDataEntity) = viewModelScope.launch {
        _mainScreenData.emit(data)
        obtainEvent(Event.OnLoadingNetworkStarted)
    }

    private fun startNetworkLoading() = viewModelScope.launch {
        mainScreenRepository.getServerMainScreenData()
            .onSuccess {
                obtainEvent(Event.OnLoadingNetworkSucceed(it))
            }.onFailure {
                obtainEvent(Event.OnLoadingFailed(it.localizedMessage))
            }
    }

    private fun succeedNetworkLoading(data: MainScreenDataEntity) = viewModelScope.launch {
        _mainScreenData.emit(data)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun deleteWallet(walletId: Long) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        deleteWalletRepository.deleteWallet(walletId = walletId)
            .onSuccess {
                _mainScreenData.emit(
                    _mainScreenData.value.copy(wallets = _mainScreenData.value.wallets.filter { wallet ->
                        !(wallet.id == walletId && it)
                    })
                )
                _loadStateData.emit(LoadingState.LOAD_SUCCEED)
            }.onFailure {
                _loadStateData.emit(LoadingState.LOAD_FAILED)
            }
    }

    sealed class Event : BaseEvent() {
        object OnLoadingStarted : Event()
        class OnLoadingDBSucceed(val data: MainScreenDataEntity) : Event()
        object OnLoadingNetworkStarted : Event()
        class OnLoadingNetworkSucceed(val data: MainScreenDataEntity) : Event()
        class OnLoadingFailed(val error: String?) : Event()
        class DeleteWallet(val walletId: Long) : Event()
    }
}

package ru.alexbur.smartwallet.ui.filter.wallets

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.repositories.MainScreenRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FilterWalletsViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository
) : BaseViewModel<FilterWalletsViewModel.Event>() {

    val walletsData: StateFlow<List<WalletEntity>>
        get() = _walletsData.asStateFlow()

    private val _walletsData = MutableStateFlow(MainScreenDataEntity.shimmerData.wallets)
    private val allWallets = mutableListOf<WalletEntity>()

    init {
        obtainEvent(Event.OnLoadingStarted)
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading()
            }
            is Event.OnLoadingDBSucceed -> {
                succeedDbLoading(event.data)
            }
            is Event.OnLoadingNetworkStarted -> {
                startNetworkLoading()
            }
            is Event.OnLoadingNetworkSucceed -> {
                succeedNetworkLoading(event.data)
            }
            is Event.OnLoadingFailed -> {
                failLoading(event.error)
            }
            is Event.FilterWallets -> {
                filterWallets(event.filter)
            }
        }
    }

    private fun startLoading() = viewModelScope.launch {
        val data = mainScreenRepository.getDbMainScreenData().getOrNull()?.wallets
            ?: MainScreenDataEntity.shimmerData.wallets
        obtainEvent(
            Event.OnLoadingDBSucceed(
                data
            )
        )
    }

    private fun succeedDbLoading(data: List<WalletEntity>) = viewModelScope.launch {
        allWallets.addAll(data)
        _walletsData.emit(allWallets)
        obtainEvent(Event.OnLoadingNetworkStarted)
    }

    private fun startNetworkLoading() = viewModelScope.launch {
        mainScreenRepository.getServerMainScreenData().onSuccess {
            obtainEvent(Event.OnLoadingNetworkSucceed(it.wallets))
        }.onFailure {
            obtainEvent(Event.OnLoadingFailed(it.localizedMessage))
        }
    }

    private fun succeedNetworkLoading(data: List<WalletEntity>) = viewModelScope.launch {
        allWallets.clear()
        allWallets.addAll(data)
        _walletsData.emit(allWallets)
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _walletsData.emit(emptyList())
    }

    private fun filterWallets(filter: String) = viewModelScope.launch {
        _walletsData.emit(
            allWallets.filter { it.name.contains(filter) }
        )
    }

    sealed class Event : BaseEvent() {
        object OnLoadingStarted : Event()
        class OnLoadingDBSucceed(val data: List<WalletEntity>) : Event()
        object OnLoadingNetworkStarted : Event()
        class OnLoadingNetworkSucceed(val data: List<WalletEntity>) : Event()
        class OnLoadingFailed(val error: String?) : Event()
        class FilterWallets(val filter: String) : Event()
    }
}
package ru.alexbur.smartwallet.ui.wallet.detailwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel

class DetailWalletViewModel @AssistedInject constructor(
    @Assisted
    private val walletId: Long,
    private val detailWalletRepository: DetailWalletRepository,
    private val deleteTransactionRepository: DeleteTransactionRepository
) : BaseViewModel<DetailWalletViewModel.Event>() {

    val detailWalletData: StateFlow<List<DetailWalletItem>>
        get() = _detailWalletData.asStateFlow()
    val loadStateData: SharedFlow<LoadingState>
        get() = _loadStateData.asStateFlow()

    private val _detailWalletData = MutableStateFlow<List<DetailWalletItem>>(emptyList())
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    init {
        viewModelScope.launch {
            obtainEvent(Event.OnLoadingStarted(walletId = walletId))
        }
    }

    override fun obtainEvent(event: Event) {
        when (event) {
            is Event.OnLoadingStarted -> {
                startLoading(walletId = event.walletId)
            }
            is Event.OnLoadingDBSucceed -> {
                succeedDbLoading(list = event.data)
            }
            is Event.OnLoadingNetworkStarted -> {
                startNetworkLoading(walletId = event.walletId)
            }
            is Event.OnLoadingFailed -> {
                failLoading(error = event.error)
            }
            is Event.OnLoadingNetworkSucceed -> {
                succeedNetworkLoading(list = event.data)
            }
            is Event.DeleteTransaction -> {
                deleteTransaction(event.transaction)
            }
        }
    }

    private fun startLoading(walletId: Long) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)

        val data = detailWalletRepository.getDbDetailWalletData(walletId).getOrNull()
        obtainEvent(Event.OnLoadingDBSucceed(data ?: emptyList()))
    }

    private fun succeedDbLoading(list: List<DetailWalletItem>) = viewModelScope.launch {
        _detailWalletData.emit(list)
        obtainEvent(Event.OnLoadingNetworkStarted(walletId = walletId))
    }

    private fun startNetworkLoading(walletId: Long) = viewModelScope.launch {
        detailWalletRepository.getServerDetailWalletData(walletId = walletId)
            .onSuccess {
                obtainEvent(Event.OnLoadingNetworkSucceed(it))
            }.onFailure {
                obtainEvent(Event.OnLoadingFailed(it.localizedMessage))
            }
    }

    private fun succeedNetworkLoading(list: List<DetailWalletItem>) = viewModelScope.launch {
        _detailWalletData.emit(list)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun failLoading(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun deleteTransaction(deleteTransaction: DetailWalletItem.Transaction) =
        viewModelScope.launch {
            _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
            deleteTransactionRepository.deleteTransaction(deleteTransaction.id).onSuccess {
                _detailWalletData.emit(_detailWalletData.value.filter { detailWalletItem ->
                    if (detailWalletItem is DetailWalletItem.Transaction && it) {
                        detailWalletItem != deleteTransaction
                    } else {
                        true
                    }
                })
                _loadStateData.emit(LoadingState.LOAD_SUCCEED)
            }.onFailure {
                _loadStateData.emit(LoadingState.LOAD_FAILED)
            }
        }

    sealed class Event : BaseEvent() {
        class OnLoadingStarted(val walletId: Long) : Event()
        class OnLoadingNetworkStarted(val walletId: Long) : Event()
        class OnLoadingFailed(val error: String?) : Event()
        class OnLoadingDBSucceed(val data: List<DetailWalletItem>) : Event()
        class OnLoadingNetworkSucceed(val data: List<DetailWalletItem>) : Event()
        class DeleteTransaction(val transaction: DetailWalletItem.Transaction) : Event()
    }

    @AssistedFactory
    interface Factory {
        fun create(walletId: Long): DetailWalletViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            walletId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(walletId) as T
            }
        }
    }
}

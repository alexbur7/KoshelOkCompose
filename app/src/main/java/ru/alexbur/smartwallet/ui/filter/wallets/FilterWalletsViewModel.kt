package ru.alexbur.smartwallet.ui.filter.wallets

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.DeleteWalletRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FilterWalletsViewModel @Inject constructor(
    private val detailWalletRepository: DetailWalletRepository,
    private val deleteWalletRepository: DeleteWalletRepository,
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler
) : BaseViewModel<FilterWalletsViewModel.Event>() {

    val walletsData: StateFlow<List<WalletEntity>>
        get() = _walletsData.asStateFlow()
    val errorMessage: StateFlow<String>
        get() = _errorMessage.asStateFlow()

    private val _walletsData = MutableStateFlow(MainScreenDataEntity.shimmerData.wallets)
    private val _errorMessage = MutableStateFlow("")
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
            is Event.DeleteWallet -> {
                deleteWallet(event.walletId)
            }
            is Event.EditWallet -> {
                editWallet(event.walletEntity)
            }
        }
    }

    private fun startLoading() = viewModelScope.launch {
        val data = detailWalletRepository.getDbWalletData().getOrNull()
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
        detailWalletRepository.getServerWalletsData().onSuccess {
            obtainEvent(Event.OnLoadingNetworkSucceed(it))
        }.onFailure {
            obtainEvent(Event.OnLoadingFailed(errorHandler.handleError(it)))
        }
    }

    private fun succeedNetworkLoading(data: List<WalletEntity>) = viewModelScope.launch {
        allWallets.clear()
        allWallets.addAll(data)
        _walletsData.emit(allWallets)
    }

    private fun failLoading(error: String) = viewModelScope.launch {
        _errorMessage.emit(error)
        _walletsData.emit(emptyList())
    }

    private fun filterWallets(filter: String) = viewModelScope.launch {
        withContext(Dispatchers.Default) {
            _walletsData.emit(
                allWallets.filter { it.name.contains(filter, ignoreCase = true) }
            )
        }
    }

    private fun deleteWallet(id: Long) = viewModelScope.launch {
        deleteWalletRepository.deleteWallet(walletId = id)
            .onSuccess {
                _walletsData.emit(it.wallets)
            }.onFailure {
                _errorMessage.emit(errorHandler.handleError(it))
            }
    }

    private fun editWallet(walletEntity: WalletEntity) = viewModelScope.launch {
        savingDataManager.editWalletFlow.emit(
            CreateWalletEntity(
                id = walletEntity.id,
                limit = walletEntity.limit,
                name = walletEntity.name,
                currency = walletEntity.currency,
                amountMoney = walletEntity.amountMoney,
                incomeMoney = walletEntity.incomeMoney,
                consumptionMoney = walletEntity.consumptionMoney
            )
        )
    }

    sealed class Event : BaseEvent() {
        object OnLoadingStarted : Event()
        class OnLoadingDBSucceed(val data: List<WalletEntity>) : Event()
        object OnLoadingNetworkStarted : Event()
        class OnLoadingNetworkSucceed(val data: List<WalletEntity>) : Event()
        class OnLoadingFailed(val error: String) : Event()
        class FilterWallets(val filter: String) : Event()
        class DeleteWallet(val walletId: Long) : Event()
        class EditWallet(val walletEntity: WalletEntity) : Event()
    }
}
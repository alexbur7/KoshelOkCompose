package ru.alexbur.smartwallet.ui.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.CreateWalletEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.DeleteWalletRepository
import ru.alexbur.smartwallet.domain.repositories.MainScreenRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val mainScreenRepository: MainScreenRepository,
    private val deleteWalletRepository: DeleteWalletRepository,
    private val savingDataManager: SavingDataManager,
    private val errorHandler: ErrorHandler
) : BaseViewModel<ProfileViewModel.Event>() {

    val loadStateData: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val mainScreenData: StateFlow<MainScreenDataEntity>
        get() = _mainScreenData.asStateFlow()
    val errorMessage: SharedFlow<String>
        get() = _errorMessage.asSharedFlow()
    val nameFlow = mainScreenRepository.nameFlow

    private val _mainScreenData = MutableStateFlow(MainScreenDataEntity.shimmerData)
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)
    private val _errorMessage = MutableSharedFlow<String>()

    init {
        obtainEvent(Event.OnLoadingStarted)

        savingDataManager.walletFlow.filterNotNull().onEach {
            _mainScreenData.update { screenData ->
                screenData.copy(wallets = screenData.wallets + it)
            }
            savingDataManager.walletFlow.emit(null)
        }.launchIn(viewModelScope)
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
            is Event.EditWallet -> {
                editWallet(event.wallWalletEntity)
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
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        mainScreenRepository.getServerMainScreenData().onSuccess {
            obtainEvent(Event.OnLoadingNetworkSucceed(it))
        }.onFailure {
            obtainEvent(Event.OnLoadingFailed(errorHandler.handleError(it)))
        }
    }

    private fun succeedNetworkLoading(data: MainScreenDataEntity) = viewModelScope.launch {
        _mainScreenData.emit(data)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    private fun failLoading(error: String) = viewModelScope.launch {
        _mainScreenData.emit(MainScreenDataEntity.emptyData)
        _errorMessage.emit(error)
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun deleteWallet(walletId: Long) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        deleteWalletRepository.deleteWallet(walletId = walletId)
            .onSuccess {
                _mainScreenData.emit(it)
                _loadStateData.emit(LoadingState.LOAD_SUCCEED)
            }.onFailure {
                _errorMessage.emit(errorHandler.handleError(it))
                _loadStateData.emit(LoadingState.LOAD_FAILED)
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
        class OnLoadingDBSucceed(val data: MainScreenDataEntity) : Event()
        object OnLoadingNetworkStarted : Event()
        class OnLoadingNetworkSucceed(val data: MainScreenDataEntity) : Event()
        class OnLoadingFailed(val error: String) : Event()
        class DeleteWallet(val walletId: Long) : Event()
        class EditWallet(val wallWalletEntity: WalletEntity) : Event()
    }
}

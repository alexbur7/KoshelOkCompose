package ru.alexbur.smartwallet.ui.auth

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.alexbur.smartwallet.data.utils.AccountDataStore
import ru.alexbur.smartwallet.domain.entities.onboarding.UserEntity
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.domain.repositories.RegistrationRepository
import ru.alexbur.smartwallet.ui.base.BaseEvent
import ru.alexbur.smartwallet.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val registrationRepository: RegistrationRepository,
    private val accountDataStore: AccountDataStore
) : BaseViewModel<AuthorizationViewModel.Event>() {


    val loadStateData: Flow<LoadingState>
        get() = _loadStateData.asStateFlow().onEach { delay(300L) }
    val errorData: StateFlow<String>
        get() = _errorData.asStateFlow()

    private val _errorData = MutableStateFlow(String())
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_DEFAULT)

    override fun obtainEvent(event: Event) {
        when(event){
            is Event.RegistrationStarted -> {
                registrationUser(event.email, event.displayName)
            }
            is Event.RegistrationFailed -> {
                registrationFailed(event.error)
            }
            is Event.RegistrationSucceed -> {
                registrationSucceed(token = event.token)
            }
        }
    }

    private fun registrationUser(email: String, displayName: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        registrationRepository.registrationUser(user = UserEntity(email))
            .onSuccess { token ->
                accountDataStore.updateEmail(email)
                accountDataStore.updateName(displayName)
                obtainEvent(Event.RegistrationSucceed(token = token))
            }.onFailure {
                obtainEvent(Event.RegistrationFailed(it.localizedMessage))
            }
    }

    private fun registrationFailed(error: String?) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_FAILED)
    }

    private fun registrationSucceed(token: String) = viewModelScope.launch {
        accountDataStore.updateToken(token)
        _loadStateData.emit(LoadingState.LOAD_SUCCEED)
    }

    sealed class Event: BaseEvent(){
        class RegistrationStarted(val email: String, val displayName: String?) : Event()
        class RegistrationFailed(val error: String?) : Event()
        class RegistrationSucceed(val token: String) : Event()
    }
}

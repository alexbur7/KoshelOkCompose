package ru.alexbur.koshelok.ui.onboarding

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.alexbur.koshelok.data.utils.AccountDataStore
import ru.alexbur.koshelok.domain.entities.onboarding.UserEntity
import ru.alexbur.koshelok.domain.enums.LoadingState
import ru.alexbur.koshelok.domain.repositories.RegistrationRepository
import ru.alexbur.koshelok.ui.base.BaseEvent
import ru.alexbur.koshelok.ui.base.BaseViewModel
import javax.inject.Inject

class OnBoardScreenViewModel @Inject constructor(
    private val registrationRepository: RegistrationRepository,
    private val accountDataStore: AccountDataStore
) : BaseViewModel<OnBoardScreenViewModel.Event>() {


    val loadStateData: StateFlow<LoadingState>
        get() = _loadStateData.asStateFlow()
    val errorData: StateFlow<String>
        get() = _errorData.asStateFlow()

    private val _errorData = MutableStateFlow(String())
    private val _loadStateData = MutableStateFlow(LoadingState.LOAD_IN_PROGRESS)

    override fun obtainEvent(event: Event) {
        when(event){
            is Event.RegistrationStarted -> {
                registrationUser(event.userEntity)
            }
            is Event.RegistrationFailed -> {
                registrationFailed(event.error)
            }
            is Event.RegistrationSucceed -> {
                registrationSucceed(token = event.token)
            }
        }
    }

    private fun registrationUser(userEntity: UserEntity) = viewModelScope.launch {
        _loadStateData.emit(LoadingState.LOAD_IN_PROGRESS)
        registrationRepository.registrationUser(user = userEntity)
            .onSuccess { token ->
                accountDataStore.updateEmail(userEntity.email)
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
        class RegistrationStarted(val userEntity: UserEntity) : Event()
        class RegistrationFailed(val error: String?) : Event()
        class RegistrationSucceed(val token: String) : Event()
    }
}

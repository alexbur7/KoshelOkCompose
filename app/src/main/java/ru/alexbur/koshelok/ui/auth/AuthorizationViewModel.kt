package ru.alexbur.koshelok.ui.auth

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.alexbur.koshelok.ui.base.BaseEvent
import ru.alexbur.koshelok.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor() : BaseViewModel<AuthorizationViewModel.Event>() {

    override fun obtainEvent(event: Event) {
    }


    sealed class Event : BaseEvent() {

    }
}
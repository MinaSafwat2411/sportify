package com.faswet.sportify.ui.screens.login.contract

import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState
import com.google.firebase.auth.FirebaseUser

class LoginContract {
    sealed class Event : ViewEvent {
        data class Login(val email: String, val password: String) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val firebaseUser: FirebaseUser? = null,
        val errorMessage: String? = null,
        val email: String = "",
        val password: String = ""
    ): ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToLayout : Navigation()
        }
    }
}
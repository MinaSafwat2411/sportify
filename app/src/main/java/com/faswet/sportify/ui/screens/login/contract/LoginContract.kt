package com.faswet.sportify.ui.screens.login.contract

import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState
import com.google.firebase.auth.FirebaseUser

class LoginContract {
    sealed class Event : ViewEvent {
        data class OnEmailChanged(val email: String) : Event()
        data class OnPasswordChanged(val password: String) : Event()
        data object OnLoginClicked : Event()
        data object OnSignupClicked : Event()
        data object OnForgotPasswordClicked : Event()
        data object OnGoogleLoginClicked : Event()
        data object OnFacebookLoginClicked : Event()

        data object OnShowHidePasswordClicked : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val firebaseUser: FirebaseUser? = null,
        val errorMessage: String? = null,
        val emailError: Int? = null,
        val passwordError: Int? = null,
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
    ): ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToLayout : Navigation()
        }
    }
}
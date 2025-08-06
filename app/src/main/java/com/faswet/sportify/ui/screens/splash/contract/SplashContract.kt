package com.faswet.sportify.ui.screens.splash.contract

import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState

class SplashContract {
    sealed class Event : ViewEvent {
        data object OnNavigation : Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
    ): ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToLayout : Navigation()
            data object ToLogin : Navigation()
            data object ToOnBoarding : Navigation()
        }
    }
}
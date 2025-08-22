package com.faswet.sportify.ui.main.contract

import android.content.Context
import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState
import com.faswet.sportify.ui.main.NavigationScreen
import com.faswet.sportify.ui.screens.login.contract.LoginContract.Effect

class MainContract {

    sealed class Event : ViewEvent {
        data class ChangeLanguage(val langCode: String, val context: Context) : Event()
        data class ToggleTheme(val isDarkMode: Boolean) : Event()
        data object OnBackClicked : Event()
    }

    data class State(
        val shouldShowBottomNavBar: Boolean = false,
        val isDarkTheme: Boolean? = null,
        val currentLanguage: String = "en"
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object ToLayout : Navigation()
        }
    }
}
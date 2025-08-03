package com.faswet.sportify.ui.main.contract

import android.content.Context
import com.faswet.sportify.ui.base.ViewEvent
import com.faswet.sportify.ui.base.ViewSideEffect
import com.faswet.sportify.ui.base.ViewState
import com.faswet.sportify.ui.main.NavigationScreen

class MainContract {

    sealed class Event : ViewEvent {
        data object ShowBottomNavigationBar : Event()
        data object HideBottomNavigationBar : Event()

        data class NavigateItem(val item: NavigationScreen.BottomNavItem): Event()
        data class ChangeLanguage(val langCode: String, val context: Context) : Event()
        data class ToggleTheme(val isDarkMode: Boolean) : Event()
    }

    data class State(
        val shouldShowBottomNavBar: Boolean = false,
        val selectedItem: NavigationScreen.BottomNavItem = NavigationScreen.BottomNavItem.Home,
        val isDarkTheme: Boolean? = null,
        val currentLanguage: String = "en"
    ) : ViewState

    sealed class Effect : ViewSideEffect
}
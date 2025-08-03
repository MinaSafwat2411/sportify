package com.faswet.sportify.ui.main

import android.content.Context
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.main.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    init {
        val isDark = loadThemeFromPrefs()
        val lang = loadLangFromPrefs()

        setState {
            copy(
                isDarkTheme = isDark,
                currentLanguage = lang
            )
        }
    }
    override fun setInitialState() = MainContract.State(
        shouldShowBottomNavBar = false,
        selectedItem = NavigationScreen.BottomNavItem.Home,
        isDarkTheme = null,
        currentLanguage = "en"
    )

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.HideBottomNavigationBar -> {
                setState { copy(shouldShowBottomNavBar = false) }
            }

            is MainContract.Event.ShowBottomNavigationBar -> {
                setState { copy(shouldShowBottomNavBar = true) }
            }

            is MainContract.Event.NavigateItem -> {
                setState { copy(selectedItem = event.item) }
            }

            is MainContract.Event.ChangeLanguage -> {
                saveLangToPrefs(event.langCode)
                setState { copy(currentLanguage = event.langCode) }
            }

            is MainContract.Event.ToggleTheme -> {
                saveThemeToPrefs(event.isDarkMode)
                setState { copy(isDarkTheme = event.isDarkMode) }
            }
        }
    }

    private fun saveLangToPrefs(lang: String) {
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit()
            .putString("lang", lang)
            .apply()
    }

    private fun saveThemeToPrefs(isDark: Boolean) {
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("theme", isDark)
            .apply()
    }

    private fun loadLangFromPrefs(): String {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getString("lang", "en") ?: "en"
    }

    private fun loadThemeFromPrefs(): Boolean {
        return context.getSharedPreferences("settings", Context.MODE_PRIVATE)
            .getBoolean("theme", false)
    }
}

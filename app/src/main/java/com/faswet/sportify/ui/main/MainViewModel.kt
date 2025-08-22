package com.faswet.sportify.ui.main

import android.content.Context
import com.faswet.sportify.data.repositories.main.IMainRepository
import com.faswet.sportify.ui.base.BaseViewModel
import com.faswet.sportify.ui.main.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.faswet.sportify.utils.extentions.updateLocale
import java.util.Locale


@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val iMainRepository: IMainRepository
) : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    init {
        setState {
            copy(
                isDarkTheme = iMainRepository.getIsDark(),
                currentLanguage = iMainRepository.getLang()
            )
        }
    }
    override fun setInitialState() = MainContract.State(
        isDarkTheme = null,
        currentLanguage = "en"
    )

    override fun handleEvents(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.ChangeLanguage -> {
                saveLangToPrefs(event.langCode,event.context)
            }

            is MainContract.Event.ToggleTheme -> {
                saveThemeToPrefs(event.isDarkMode)
            }
            MainContract.Event.OnBackClicked -> {
                setEffect { MainContract.Effect.Navigation.ToLayout }
            }
        }
    }

    private fun saveLangToPrefs(lang: String,context: Context) {
        iMainRepository.setLang(lang)
        setState { copy(currentLanguage = lang) }
    }

    private fun saveThemeToPrefs(isDark: Boolean) {
        iMainRepository.setIsDark(isDark)
        setState { copy(isDarkTheme = isDark) }
    }
    fun getLang(): String {
        return iMainRepository.getLang()
    }

    fun getIsDark(): Boolean {
        return iMainRepository.getIsDark()
    }
}

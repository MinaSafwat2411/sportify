package com.faswet.sportify.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.faswet.sportify.R
import com.faswet.sportify.ui.base.compose.IBaseBottomNavigationBar
import com.faswet.sportify.ui.main.contract.MainContract
import com.faswet.sportify.ui.theme.Mirage
import com.faswet.sportify.ui.theme.SportifyTheme
import com.faswet.sportify.ui.theme.White
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.platform.LocalConfiguration
import com.faswet.sportify.utils.extentions.restartApp
import com.faswet.sportify.utils.loacl.LocaleHelper

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IBaseBottomNavigationBar {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lang = viewModel.getLang()
        val  isDark = viewModel.getIsDark()
        LocaleHelper.setLocale(this, lang)
        enableEdgeToEdge()
        actionBar?.hide()
        setContent {
            val state by viewModel.viewState
            val navController = rememberNavController()

            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !(state.isDarkTheme ?: false)
            val backgroundColor = if (state.isDarkTheme == true) Mirage else White

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = backgroundColor,
                    darkIcons = useDarkIcons
                )
            }
            RTLWrapper {
                SportifyTheme(
                    darkTheme = state.isDarkTheme == true,
                    dynamicColor = false
                ) {
                    AppNavHost(viewModel = viewModel, navController = navController)
                }
            }
        }
    }

    override fun showBottomNavigation() {
        viewModel.setEvent(MainContract.Event.ShowBottomNavigationBar)
    }

    override fun hideBottomNavigation() {
        viewModel.setEvent(MainContract.Event.HideBottomNavigationBar)
    }
}


@Composable
fun ThemeAndLanguageControls(viewModel: MainViewModel) {
    val context = LocalContext.current
    val  activity = context as MainActivity
    val state by viewModel.viewState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                val newTheme = !(state.isDarkTheme ?: false)
                viewModel.setEvent(MainContract.Event.ToggleTheme(newTheme))
            }) {
                Text(
                    text = if (state.isDarkTheme == true) "Switch to Light" else "Switch to Dark",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val newLang = if (state.currentLanguage == "en") "ar" else "en"
                viewModel.setEvent(MainContract.Event.ChangeLanguage(newLang, context))
                activity.restartApp()
            }) {
                Text(
                    stringResource(R.string.switch_language),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}


@Composable
fun RTLWrapper(content: @Composable () -> Unit) {
    val lang = LocalConfiguration.current.locales[0].language
    val isRtl = lang == "ar"

    CompositionLocalProvider(
        LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
    ) {
        content()
    }
}
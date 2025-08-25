package com.faswet.sportify.ui.main


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.faswet.sportify.R
import com.faswet.sportify.ui.main.contract.MainContract
import com.faswet.sportify.ui.theme.SportifyTheme
import com.faswet.sportify.ui.theme.Transparent
import com.faswet.sportify.utils.extentions.restartApp
import com.faswet.sportify.utils.loacl.LocaleHelper
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        val lang = viewModel.getLang()
        val  isDark = viewModel.getIsDark()
        AppCompatDelegate.setDefaultNightMode(if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
        LocaleHelper.setLocale(this, lang)
        enableEdgeToEdge()
        actionBar?.hide()
        setContent {
            val state by viewModel.viewState
            val navController = rememberNavController()

            val configuration = LocalConfiguration.current
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !(state.isDarkTheme ?: false)


            LaunchedEffect(configuration.orientation, state.isDarkTheme) {
                systemUiController.setSystemBarsColor(
                    color = Transparent,
                    darkIcons = useDarkIcons
                )
            }
            RTLWrapper {
                SportifyTheme(
                    darkTheme = state.isDarkTheme == true,
                    dynamicColor = false
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        AppNavHost(viewModel = viewModel, navController = navController)
                    }
                }
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
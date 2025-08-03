package com.faswet.sportify.ui.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.faswet.sportify.ui.base.compose.IBaseBottomNavigationBar
import com.faswet.sportify.ui.main.contract.MainContract
import com.faswet.sportify.ui.theme.SportifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IBaseBottomNavigationBar {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val lang = getSharedPreferences("settings", MODE_PRIVATE).getString("lang", "en") ?: "en"
        val isDark = getSharedPreferences("settings", MODE_PRIVATE).getBoolean("theme", false)

        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        setContent {
            val state by viewModel.viewState
            SportifyTheme(darkTheme = state.isDarkTheme == true) {
                ThemeAndLanguageControls(viewModel)
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
    val state by viewModel.viewState

    Column {
        Button(onClick = {
            val newTheme = !(state.isDarkTheme?:false)
            viewModel.setEvent(MainContract.Event.ToggleTheme(newTheme))
            restartApp(context)
        }) {
            Text(if (state.isDarkTheme == true) "Switch to Light" else "Switch to Dark")
        }

        Button(onClick = {
            val newLang = if (state.currentLanguage == "en") "ar" else "en"
            viewModel.setEvent(MainContract.Event.ChangeLanguage(newLang))
            restartApp(context)
        }) {
            Text("Switch Language")
        }
    }
}
fun restartApp(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(intent)
    Runtime.getRuntime().exit(0)
}
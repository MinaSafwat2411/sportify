package com.faswet.sportify.ui.main

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faswet.sportify.R
import com.faswet.sportify.utils.activity.findActivity


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = NavigationScreen.Splash.route,
) {

    val context = LocalContext.current
    var shouldShowBottomNavigationBar by rememberSaveable {
        mutableStateOf(false)
    }

    var isHomeResponseFinished by rememberSaveable {
        mutableStateOf(true)
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.ime, modifier = Modifier
            .windowInsetsPadding(WindowInsets.safeDrawing),
        bottomBar = {
        }) { padding ->
        NavHost(
            modifier = modifier.padding(padding),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavigationScreen.Splash.route) {
                ThemeAndLanguageControls(viewModel)
            }
            composable(NavigationScreen.OnBoarding.route) {

            }
            composable(NavigationScreen.ChooseWay.route) {

            }
            composable(NavigationScreen.Login.route) {

            }
            composable(NavigationScreen.PinAccess.route) {

            }

            composable(NavigationScreen.Signup.route) {

            }

        }

    }

    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
    }

    fun replaceRouteArguments(route: String, arguments: Bundle): String {
        var resolvedRoute = route
        arguments.keySet().forEach { key ->
            val value = arguments.get(key)?.toString() ?: ""
            resolvedRoute = resolvedRoute.replace("{$key}", value)
        }
        return resolvedRoute
    }
}
@Composable
private fun ShouldShowNavigationBottomBar(route: String) {
    val activity = LocalContext.current.findActivity() as? MainActivity
    when (route) {
        NavigationScreen.Splash.route,
        NavigationScreen.OnBoarding.route,
        NavigationScreen.ChooseWay.route,
        NavigationScreen.Login.route,
        NavigationScreen.PinAccess.route,
        NavigationScreen.Signup.route,

            -> activity?.hideBottomNavigation()

        else -> activity?.showBottomNavigation()
    }
}

enum class Screen {
    SPLASH, ON_BOARDING, CHOOSE_WAY, LOGIN, PIN_ACCESS, SIGN_UP
}

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen(Screen.SPLASH.name)
    data object OnBoarding : NavigationScreen(Screen.ON_BOARDING.name)
    data object ChooseWay : NavigationScreen(Screen.CHOOSE_WAY.name)
    data object Login : NavigationScreen(Screen.LOGIN.name)
    data object PinAccess : NavigationScreen(Screen.PIN_ACCESS.name)
    data object Signup: NavigationScreen(Screen.SIGN_UP.name)



    sealed class BottomNavItem(val route: String, @DrawableRes val icon: Int) {
        data object Home : BottomNavItem("home", R.drawable.ic_home)

    }
}
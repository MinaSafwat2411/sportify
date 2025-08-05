package com.faswet.sportify.ui.main

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faswet.sportify.ui.screens.login.LoginScreenDestination
import com.faswet.sportify.ui.screens.onboarding.OnBoardingDestination
import com.faswet.sportify.ui.screens.splash.SplashScreenDestination
import com.faswet.sportify.utils.activity.findActivity


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
    startDestination: String = NavigationScreen.Splash.route,
) {

    val context = LocalContext.current
    NavHost(
        modifier = modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationScreen.Splash.route) {
            SplashScreenDestination(navController = navController)
        }
        composable(NavigationScreen.OnBoarding.route) {
            OnBoardingDestination(navController = navController)
        }
        composable(NavigationScreen.Login.route) {
            LoginScreenDestination(navController = navController)
        }
        composable(NavigationScreen.PinAccess.route) {

        }
        composable(NavigationScreen.Signup.route) {

        }
        composable(NavigationScreen.Layout.route) {

        }

    }
    BackHandler {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        if (currentRoute != null) {
            when (currentRoute) {
                NavigationScreen.Splash.route,
                NavigationScreen.OnBoarding.route,
                NavigationScreen.Login.route,
                NavigationScreen.Signup.route,
                NavigationScreen.Layout.route,
                    -> {
                    context.findActivity()?.finish()
                }

                else -> {
                    navController.popBackStack()
                }
            }
        }
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


enum class Screen {
    SPLASH, ON_BOARDING, LOGIN, PIN_ACCESS, SIGN_UP, LAYOUT
}

sealed class NavigationScreen(val route: String) {
    data object Splash : NavigationScreen(Screen.SPLASH.name)
    data object OnBoarding : NavigationScreen(Screen.ON_BOARDING.name)
    data object Login : NavigationScreen(Screen.LOGIN.name)
    data object PinAccess : NavigationScreen(Screen.PIN_ACCESS.name)
    data object Signup : NavigationScreen(Screen.SIGN_UP.name)

    data object Layout : NavigationScreen(Screen.LAYOUT.name)

}
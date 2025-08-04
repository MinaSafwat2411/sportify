package com.faswet.sportify.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.main.NavigationScreen
import com.faswet.sportify.ui.screens.splash.compose.SplashScreen
import com.faswet.sportify.ui.screens.splash.contract.SplashContract

@Composable
fun SplashScreenDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    SplashScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is SplashContract.Effect.Navigation.ToHome -> {
                    navController.navigate(NavigationScreen.BottomNavItem.Home.route){
                        popUpTo(NavigationScreen.Splash.route){
                            inclusive = true
                        }
                    }
                }
                is SplashContract.Effect.Navigation.ToLogin -> {
                    navController.navigate(NavigationScreen.Login.route){
                        popUpTo(NavigationScreen.Splash.route){
                            inclusive = true
                        }
                    }
                }
                is SplashContract.Effect.Navigation.ToOnBoarding -> {
                    navController.navigate(NavigationScreen.OnBoarding.route){
                        popUpTo(NavigationScreen.Splash.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    )
}
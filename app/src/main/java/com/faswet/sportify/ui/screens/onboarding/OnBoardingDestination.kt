package com.faswet.sportify.ui.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.main.NavigationScreen
import com.faswet.sportify.ui.screens.onboarding.compose.OnBoardingScreen
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract

@Composable
fun OnBoardingDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnBoardingScreen(
        modifier = modifier,
        state = viewModel.viewState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
        effectFlow = viewModel.effect,
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                OnBoardingContract.Effect.Navigation.ToLogin -> {
                    navController.navigate(NavigationScreen.Login.route){
                        popUpTo(NavigationScreen.OnBoarding.route) {
                            inclusive = true
                        }
                    }
                }
            }
        },
    )
}
package com.faswet.sportify.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.main.NavigationScreen
import com.faswet.sportify.ui.screens.login.compose.LoginScreen
import com.faswet.sportify.ui.screens.login.contract.LoginContract

@Composable
fun LoginScreenDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    LoginScreen(
        modifier = modifier,
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is LoginContract.Effect.Navigation.ToLayout -> {
                    navController.navigate(NavigationScreen.Layout.route){
                        popUpTo(NavigationScreen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    )
}
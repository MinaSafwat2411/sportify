package com.faswet.sportify.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.main.MainViewModel
import com.faswet.sportify.ui.main.contract.MainContract
import com.faswet.sportify.ui.screens.settings.compose.SettingsScreen

@Composable
fun SettingsDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel,
) {
    SettingsScreen(
        modifier = modifier,
        state = viewModel.viewState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is MainContract.Effect.Navigation.ToLayout) {
                navController.popBackStack()
            }
        },
        viewModel = viewModel
    )
}
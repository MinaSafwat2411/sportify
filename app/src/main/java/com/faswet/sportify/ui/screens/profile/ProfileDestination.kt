package com.faswet.sportify.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import com.faswet.sportify.ui.screens.profile.coompose.ProfileScreen

@Composable
fun ProfileDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    ProfileScreen(
        modifier = modifier,
        state = viewModel.viewState.value,
        onEventSent = { event -> viewModel.setEvent(event) },
        effectFlow = viewModel.effect,
        onNavigationRequested = { navigationEffect ->
            when (navigationEffect) {
                is ProfileContract.Effect.Navigation.Back -> {
                    navController.popBackStack()
                }
            }
        },
    )
}
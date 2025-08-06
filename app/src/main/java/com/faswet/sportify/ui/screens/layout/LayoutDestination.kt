package com.faswet.sportify.ui.screens.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.faswet.sportify.ui.screens.layout.compose.LayoutScreen
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract

@Composable
fun LayoutDestination(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LayoutViewModel = hiltViewModel(),
) {
    LayoutScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
        },
        modifier = modifier
    )
}
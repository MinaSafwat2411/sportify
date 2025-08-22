package com.faswet.sportify.ui.screens.settings.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.main.MainViewModel
import com.faswet.sportify.ui.main.contract.MainContract

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    state: MainContract.State,
    onEventSent: (event: MainContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: MainContract.Effect.Navigation) -> Unit,
    viewModel: MainViewModel
    ) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is MainContract.Effect.Navigation.ToLayout -> onNavigationRequested(effect)
            }
        }
    }
    SettingsContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent,
    )
}
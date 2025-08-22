package com.faswet.sportify.ui.screens.layout.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun LayoutScreen(
    modifier: Modifier = Modifier,
    state: LayoutContract.State,
    onEventSent: (event: LayoutContract.Event) -> Unit,
    effectFlow: Flow<LayoutContract.Effect>?,
    onNavigationRequested: (navigationEffect: LayoutContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is LayoutContract.Effect.Navigation.ToSettings -> onNavigationRequested(effect)
                is LayoutContract.Effect.Navigation.ToProfile -> onNavigationRequested(effect)
            }
        }?.collect()
    }
    LayoutContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent
    )
}
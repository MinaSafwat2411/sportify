package com.faswet.sportify.ui.screens.layout.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import kotlinx.coroutines.flow.Flow

@Composable
fun LayoutScreen(
    modifier: Modifier = Modifier,
    state: LayoutContract.State,
    onEventSent: (event: LayoutContract.Event) -> Unit,
    effectFlow: Flow<LayoutContract.Effect>?,
    onNavigationRequested: (navigationEffect: LayoutContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is LayoutContract.Effect.Navigation -> onNavigationRequested(effect)
            }
        }
    }
    LayoutContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent
    )
}
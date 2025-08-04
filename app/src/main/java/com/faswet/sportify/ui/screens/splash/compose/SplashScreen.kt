package com.faswet.sportify.ui.screens.splash.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.splash.contract.SplashContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    state: SplashContract.State,
    effectFlow:Flow<SplashContract.Effect>?,
    onEventSent: (event: SplashContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: SplashContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is SplashContract.Effect.Navigation.ToHome -> onNavigationRequested(effect)
                is SplashContract.Effect.Navigation.ToLogin -> onNavigationRequested(effect)
                is SplashContract.Effect.Navigation.ToOnBoarding -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    SplashContent(
        modifier = modifier,
        onEventSent = onEventSent,
        state = state
    )
}
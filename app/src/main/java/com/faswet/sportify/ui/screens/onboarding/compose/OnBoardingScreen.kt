package com.faswet.sportify.ui.screens.onboarding.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    state: OnBoardingContract.State,
    effectFlow: Flow<OnBoardingContract.Effect>?,
    onEventSent: (event: OnBoardingContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: OnBoardingContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is OnBoardingContract.Effect.Navigation.ToLogin -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    OnBoardingContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent
    )
}
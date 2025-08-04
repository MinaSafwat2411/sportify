package com.faswet.sportify.ui.screens.login.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.login.contract.LoginContract
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginContract.State,
    effectFlow: Flow<LoginContract.Effect>?,
    onEventSent: (event: LoginContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: LoginContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.collect { effect ->
            when (effect) {
                is LoginContract.Effect.Navigation.ToLayout -> onNavigationRequested(effect)
            }
        }
    }

    LoginContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent
    )
}
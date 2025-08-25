package com.faswet.sportify.ui.screens.profile.coompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.base.SIDE_EFFECTS_KEY
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileContract.State,
    onEventSent: (ProfileContract.Event) -> Unit,
    effectFlow: Flow<ProfileContract.Effect>,
    onNavigationRequested: (ProfileContract.Effect.Navigation) -> Unit,
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow.onEach { effect ->
            when (effect) {
                is ProfileContract.Effect.Navigation.Back -> onNavigationRequested(effect)
            }
        }.collect()
    }
    ProfileContent(
        modifier = modifier,
        state = state,
        onEventSent = onEventSent,
    )
}